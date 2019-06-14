package com.zc.async;

import com.zc.model.FlightInfo;
import com.zc.model.FlightInfoPo;
import com.zc.model.PolitInfo;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;
import org.codejargon.fluentjdbc.api.query.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.*;

public class AsyncFlihgtInfoRequest extends AbstractDatabaseRequest<Tuple2<FlightInfo, PolitInfo>, Tuple2<FlightInfoPo, PolitInfo>> {
    /**
     * The database specific client that can issue concurrent requests with callbacks
     */
    private transient Mapper<FlightInfoPo> flightMapper;
    private transient static final Logger LOG = LoggerFactory.getLogger(AsyncFlihgtInfoRequest.class);

    public AsyncFlihgtInfoRequest(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public void open(Configuration parameters) {
        super.open(parameters);
        ObjectMappers objectMappers = ObjectMappers.builder().build();
        flightMapper = objectMappers.forClass(FlightInfoPo.class);
    }

    @Override
    public void asyncInvoke(Tuple2<FlightInfo, PolitInfo> input, final ResultFuture<Tuple2<FlightInfoPo, PolitInfo>> resultFuture) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        String sqlSelect = "SELECT t.flight_no,\n" +
                "       t.std_utc,\n" +
                "       t.sta_utc,\n" +
                "       t.plan_orig_airport_cd,\n" +
                "       t.plan_dest_airport_cd,\n" +
                "       t.international_flag,\n" +
                "       t.aircraft_tail_no\n" +
                "  FROM t02_flt_opr_plan t\n" +
                " WHERE t.flight_id = :flightId ";

        final Future<FlightInfoPo> result = executor.submit(() -> {
            Optional<FlightInfoPo> data = query.select(sqlSelect)
                    .namedParam("flightId", input.f0.getMm_Leg_Id())
                    .firstResult(flightMapper);
            if (data.isPresent()) {
                return data.get();
            }
            return null;
        });

        // set the callback to be executed once the request by the client is complete
        // the callback simply forwards the result to the result future
        CompletableFuture.supplyAsync(() -> {
            try {
                return result.get();
            } catch (InterruptedException | ExecutionException e) {
                LOG.warn("Async failed on", e);
                // Normally handled explicitly.
                return null;
            }
        }).thenAccept((FlightInfoPo dbResult) -> {
            resultFuture.complete(Collections.singleton(Tuple2.of(dbResult, input.f1)));
        });
    }
}

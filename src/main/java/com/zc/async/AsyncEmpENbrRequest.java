package com.zc.async;

import com.zc.model.EmpInfoPo;
import com.zc.model.FlightCrewMsg;
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

public class AsyncEmpENbrRequest extends AbstractDatabaseRequest<Tuple2<FlightCrewMsg, EmpInfoPo>, Tuple2<FlightCrewMsg,EmpInfoPo>>  {
    /** The database specific client that can issue concurrent requests with callbacks */
    private transient Mapper<EmpInfoPo> flightMapper;
    private transient static final Logger LOG = LoggerFactory.getLogger(AsyncEmpENbrRequest.class);

    public AsyncEmpENbrRequest(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public void open(Configuration parameters) {
        super.open(parameters);
        ObjectMappers objectMappers = ObjectMappers.builder().build();
        flightMapper = objectMappers.forClass(EmpInfoPo.class);
    }

    @Override
    public void asyncInvoke(Tuple2<FlightCrewMsg, EmpInfoPo> input, final ResultFuture<Tuple2<FlightCrewMsg,EmpInfoPo>> resultFuture) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();

        // issue the asynchronous request, receive a future for result
        final Future<EmpInfoPo> result = executor.submit(() -> {
            Optional<EmpInfoPo> data = query.select("SELECT * FROM T11_HR_PERSON_ACCOUNT t\n" +
                    "WHERE t.person_id = :person_id\n" +
                    "and t.business_group_id = :business_group_id\n" +
                    "and sysdate between t.effective_start_date and t.effective_end_date")
                    .namedParam("person_id", input.f1.getPersonId())
                    .namedParam("business_group_id", input.f1.getBusinessGroupId())
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
        }).thenAccept( (EmpInfoPo dbResult) -> {
            resultFuture.complete(Collections.singleton(Tuple2.of(input.f0,dbResult)));
        });
    }
}

package com.zc.async;


import com.zc.model.EmpInfoPo;
import com.zc.model.FlightCrewMsg;
import com.zc.model.PolitInfo;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;
import org.codejargon.fluentjdbc.api.query.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.*;

public class AsyncEmpPersonIdRequest extends AbstractDatabaseRequest<Tuple2<PolitInfo, FlightCrewMsg>, Tuple3<PolitInfo,FlightCrewMsg,EmpInfoPo>>  {
    /** The database specific client that can issue concurrent requests with callbacks */
    private transient Mapper<EmpInfoPo> empMapper;
    private transient static final Logger LOG = LoggerFactory.getLogger(AsyncEmpPersonIdRequest.class);

    public AsyncEmpPersonIdRequest(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public void open(Configuration parameters) {
        super.open(parameters);
        ObjectMappers objectMappers = ObjectMappers.builder().build();
        empMapper = objectMappers.forClass(EmpInfoPo.class);
    }

    @Override
    public void asyncInvoke(Tuple2<PolitInfo, FlightCrewMsg> input, final ResultFuture<Tuple3<PolitInfo,FlightCrewMsg,EmpInfoPo>> resultFuture) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();

        // issue the asynchronous request, receive a future for result
        final Future<EmpInfoPo> result = executor.submit(() -> {
            Optional<EmpInfoPo> data = query.select("select person_id,business_group_id \n" +
                    "from  T11_HR_PERSON_INFO\n" +
                    "where NATIONAL_IDENTIFIER = :NATIONAL_IDENTIFIER\n" +
                    "and EFFECTIVE_START_DATE <= sysdate\n" +
                    "and EFFECTIVE_END_DATE >= sysdate  ")
                    .namedParam("NATIONAL_IDENTIFIER", input.f0.getLicense_No())
                    .firstResult(empMapper);
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
            resultFuture.complete(Collections.singleton(new Tuple3<PolitInfo,FlightCrewMsg,EmpInfoPo>(input.f0,input.f1,dbResult)));
        });
    }
}

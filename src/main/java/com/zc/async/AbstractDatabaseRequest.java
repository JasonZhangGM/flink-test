package com.zc.async;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.FluentJdbcBuilder;
import org.codejargon.fluentjdbc.api.query.Query;

import javax.sql.DataSource;

public abstract class AbstractDatabaseRequest<IN, OUT> extends RichAsyncFunction<IN, OUT> {
    protected transient Query query;
    private String url;
    private String username;
    private String password;

    public AbstractDatabaseRequest(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    protected DataSource setupDataSource() {
        ConnectionFactory connectionFactory =
                new DriverManagerConnectionFactory(
                        url, username, password);

        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool =
                new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);

        PoolingDataSource<PoolableConnection> dataSource =
                new PoolingDataSource<>(connectionPool);
        return dataSource;
    }

    @Override
    public void open(Configuration parameters) {
        FluentJdbc fluentJdbc = new FluentJdbcBuilder()
                .connectionProvider(setupDataSource())
                .build();
        query = fluentJdbc.query();
    }
}

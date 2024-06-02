package com.haole.logistics.r2dbc.service.snowflake;

public class SnowflakeWorkerConstants {

    public static final String TABLE_NAME = "snowflake_worker";
    public static final String PK_NAME = "group_name";
    public static final String DEFAULT_GROUP = "DefaultGroup";
    public static final String WORKER_ID_FIELD = "latest_worker_id";
    public static final String DATA_VERSION_FIELD = "data_version";
    public static final String QUERY_SQL = String.format("select %s, %s from %s where %s = ?", "latest_worker_id", "data_version", "snowflake_worker", "group_name");
    public static final String UPDATE_SQL = String.format("update %s set %s = ?, %s = ? where %s = ? and %s = ?", "snowflake_worker", "latest_worker_id", "data_version", "group_name", "data_version");

    SnowflakeWorkerConstants() {
    }
}

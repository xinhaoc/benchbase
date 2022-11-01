package com.oltpbenchmark.benchmarks.tpce.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class GeneratorUtils {

    public int getRandomPercents() {
        return 0;
    }

    public static void addRow(PreparedStatement preparedStatement, Object[] tuple, String tablename) throws SQLException {

        int index = 1;
        for (Object value : tuple) {
            if (value instanceof Integer) {
                preparedStatement.setInt(index, (Integer) value);
            } else if (value instanceof Long) {
                preparedStatement.setLong(index, (Long) value);
            } else if (value instanceof Double) {
                preparedStatement.setDouble(index, (Double) value);
            } else if (value instanceof String) {
                preparedStatement.setString(index, (String) value);
            } else if (value instanceof Boolean) {
                preparedStatement.setBoolean(index, (Boolean) value);
            }else if(value instanceof Short) {
                preparedStatement.setShort(index, (Short) value);
            }
            else if(value instanceof TimestampType){
                long millis = TimeUnit.MILLISECONDS.convert(((TimestampType) value).getTime(), TimeUnit.MICROSECONDS);
                Timestamp time = new Timestamp(millis);
                preparedStatement.setTimestamp(index, time);
            }
            else{
            }
            index++;
        }
        preparedStatement.addBatch();
    }
}

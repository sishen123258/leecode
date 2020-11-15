package sql;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.table.api.*;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.FileSystem;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.table.sources.StreamTableSource;
import org.apache.flink.table.sources.TableSource;
import org.apache.flink.types.Row;
import org.apache.flink.util.CloseableIterator;
import scala.Tuple2;

import java.util.UUID;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.lit;

public class TableTimeAttr {

    public static void main(String[] args) throws Exception {

        EnvironmentSettings fsSettings =
                EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamExecutionEnvironment fsEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(fsEnv, fsSettings);
        fsEnv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        String sourceDDL =
                "CREATE TABLE my_user(\n" +
                " userId String,\n" +
                " ts AS localtimestamp,\n" +
                " WATERMARK FOR ts AS ts\n" +
                ") WITH (\n" +
                " 'connector' = 'datagen',\n" +
                " 'rows-per-second'='500',\n" +
                " 'fields.userId.kind'='sequence',\n" +
                " 'fields.userId.start'='1',\n" +
                " 'fields.userId.end'='100'\n" +
                ")";

        String sinkDDL = "CREATE TABLE print (\n" +
                " userId STRING,\n" +
                " myTime TIMESTAMP(3),\n" +
                " cnt BIGINT\n" +
                ") WITH (\n" +
                " 'connector' = 'print'\n" +
                ")";

        tableEnv.executeSql(sourceDDL);
        tableEnv.executeSql(sinkDDL);

        String sql ="insert into print "+
                "select userId," +
                        "TUMBLE_START(ts,INTERVAL '10' SECOND) as myTime," +
                        "count(userId) as cnt from my_user " +
                        "group by userId,TUMBLE(ts,INTERVAL '10' SECOND)";

//        String plan = tableEnv.explainSql(sql);
//        System.out.println(plan);

        tableEnv.executeSql(sql);

    }




}

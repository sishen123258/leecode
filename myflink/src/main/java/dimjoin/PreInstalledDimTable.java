package dimjoin;


import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://blog.csdn.net/chybin500/article/details/106482620
 *
 * 预加载维表
 * 热存储维表 hbase/mysql
 * 广播维表
 * Temporal table function join
 */
public class PreInstalledDimTable {


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        List<Tuple2<String, Integer>> arrays = Arrays.asList(
                new Tuple2<String, Integer>("a", 1),
                new Tuple2<String, Integer>("b", 2),
                new Tuple2<String, Integer>("c", 3)
                );
        DataStreamSource<Tuple2<String, Integer>> cityCodeStream = env.fromCollection(arrays);


        cityCodeStream.map(new StaticDataJoin()).print();

        env.execute("demo for join");

    }

    static class StaticDataJoin extends RichMapFunction<Tuple2<String, Integer>, Tuple3<String, Integer, String>>{


        Map<String,String> dim=new HashMap<String, String>();

        @Override
        public void open(Configuration parameters) throws Exception {

            dim.put("a","BeiJing");
            dim.put("b","ShangHai");
            dim.put("c","Xian");

        }

        public Tuple3<String, Integer, String> map(Tuple2<String, Integer> value) throws Exception {

            String v3 = this.dim.get(value.f0);

            return new Tuple3<String, Integer, String>(value.f0,value.f1,v3);
        }
    }

    


}

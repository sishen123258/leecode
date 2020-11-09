package dimjoin;

import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.List;

public class BroadCastDimTable {

    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();

        List<Tuple2<String, Integer>> arrays = Arrays.asList(
                new Tuple2<String, Integer>("a", 1),
                new Tuple2<String, Integer>("b", 2),
                new Tuple2<String, Integer>("c", 3)
        );

        List<Tuple2<String, String>> cityName = Arrays.asList(
                new Tuple2<String, String>("a", "Beijing"),
                new Tuple2<String, String>("b", "ShangHai"),
                new Tuple2<String, String>("c", "GuangZhou")
        );

        final MapStateDescriptor<String, String> broadcastDesc = new MapStateDescriptor("broad1", String.class, String.class);
        BroadcastStream<Tuple2<String, String>> broadcast = env.fromCollection(cityName).broadcast(broadcastDesc);

        DataStreamSource<Tuple2<String, Integer>> cityCodeStream = env.fromCollection(arrays);

        cityCodeStream.connect(broadcast).process(
                new BroadcastProcessFunction<Tuple2<String, Integer>, Tuple2<String, String>, Tuple3<String, Integer, String>>() {

                    public void processElement(Tuple2<String, Integer> value, ReadOnlyContext ctx, Collector<Tuple3<String, Integer, String>> out) throws Exception {

                        ReadOnlyBroadcastState<String, String> state = ctx.getBroadcastState(broadcastDesc);
                        System.out.println("收到数据:"+value);
                        String cityName = "";
                        if (state.contains(value.f0)) {
                            cityName = state.get(value.f0);
                        }
                        out.collect(new Tuple3<String, Integer, String>(value.f0, value.f1, cityName));
                    }

                    public void processBroadcastElement(Tuple2<String, String> value, Context ctx, Collector<Tuple3<String, Integer, String>> out) throws Exception {
                        System.out.println("收到广播数据：" + value);
                        ctx.getBroadcastState(broadcastDesc).put(value.f0, value.f1);
                    }
                }
        ).print();

        env.execute();


    }


}

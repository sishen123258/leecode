package dimjoin;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.async.AsyncFunction;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class AsyncIOJoin {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env=StreamExecutionEnvironment.createLocalEnvironment();

        List<Tuple2<String, Integer>> arrays = Arrays.asList(
                new Tuple2<String, Integer>("a", 1),
                new Tuple2<String, Integer>("b", 2),
                new Tuple2<String, Integer>("c", 3)
        );
        DataStreamSource<Tuple2<String, Integer>> cityCodeStream = env.fromCollection(arrays);

        SingleOutputStreamOperator<Tuple3<String, Integer, String>> orderedWait = AsyncDataStream.orderedWait(cityCodeStream, new JoinWithAsyncFunc(), 1, TimeUnit.SECONDS, 5);
        SingleOutputStreamOperator<Tuple3<String, Integer, String>> unorderedWait = AsyncDataStream.unorderedWait(cityCodeStream, new JoinWithAsyncFunc(), 1, TimeUnit.SECONDS, 5);

        orderedWait.print();
        unorderedWait.print();
        env.execute();
    }

    static class JoinWithAsyncFunc extends RichAsyncFunction<Tuple2<String, Integer>, Tuple3<String, Integer, String>> {

        Map<String,String> dim=new HashMap<String, String>();



        @Override
        public void open(Configuration parameters) throws Exception {
            dim.put("a","BeiJing");
            dim.put("b","ShangHai");
            dim.put("c","Xian");
        }

        public void asyncInvoke(Tuple2<String, Integer> input, ResultFuture<Tuple3<String, Integer, String>> resultFuture) throws Exception {

            String name = dim.get(input.f0);


            List<Tuple3<String,Integer,String>> arr=new ArrayList<Tuple3<String, Integer, String>>();

            arr.add(new Tuple3<String, Integer, String>(input.f0,input.f1,name));

            resultFuture.complete(arr);

        }


        public void timeout(Tuple2<String, Integer> input, ResultFuture<Tuple3<String, Integer, String>> resultFuture) throws Exception {

        }
    }


}

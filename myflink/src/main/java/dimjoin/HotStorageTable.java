package dimjoin;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.cache.*;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HotStorageTable {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env=StreamExecutionEnvironment.createLocalEnvironment();

        List<Tuple2<String, Integer>> arrays = Arrays.asList(
                new Tuple2<String, Integer>("a", 1),
                new Tuple2<String, Integer>("b", 2),
                new Tuple2<String, Integer>("c", 3)
        );
        DataStreamSource<Tuple2<String, Integer>> cityCodeStream = env.fromCollection(arrays);

        cityCodeStream.map(new MapJoinInHbase()).print();

        env.execute();

    }

    static class MapJoinInHbase extends RichMapFunction<Tuple2<String, Integer>, Tuple3<String, Integer,String>> {

        LoadingCache<String, String> cache;
        @Override
        public void open(Configuration parameters) throws Exception {

            cache = CacheBuilder.newBuilder()//最多缓存个数，超过了就根据最近最少使用算法来移除缓存
                    .maximumSize(1000)
                    //在更新后的指定时间后就回收
                    .expireAfterWrite(10, TimeUnit.MINUTES)
                    //指定移除通知
                    .removalListener(new RemovalListener<String, String>() {
                        public void onRemoval(RemovalNotification<String, String> removalNotification) {
                            System.out.println(removalNotification.getKey() + "被移除了，值为：" + removalNotification.getValue());
                        }
                    }).build( //指定加载缓存的逻辑
                            new CacheLoader<String, String>() {
                                @Override
                                public String load(String cityId) throws Exception {
                                    String cityName = readFromHbase(cityId);
                                    return cityName;
                                }
                            });
        }

        private String readFromHbase(String cityId) {
            Map<String,String> dim=new HashMap<String, String>();
            dim.put("a","BeiJing");
            dim.put("b","ShangHai");
            dim.put("c","Xian");

            String cityName = "";
            if (dim.containsKey(cityId)) {
                cityName = dim.get(cityId);
            }

            return cityName;
        }

        public Tuple3<String, Integer, String> map(Tuple2<String, Integer> value) throws Exception {

            String name = cache.get(value.f0);

            return new Tuple3<String, Integer, String>(value.f0,value.f1,name);
        }
    }

}

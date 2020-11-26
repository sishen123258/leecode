package cep;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.functions.PatternProcessFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


class Event {
    String name;
    int value;

    public Event() {
    }

    public Event(String name, int value) {
        this.name = name;
        this.value = value;
    }
}

public class CEPTraining {


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();

        List<Event> arrays = Arrays.asList(
                new Event("a", 1),
                new Event("b", 0),
                new Event("c", 3)
        );

        DataStreamSource<Event> stream = env.fromCollection(arrays);

        Pattern<Event, Event> pattern = Pattern.<Event>begin("start").where(new SimpleCondition<Event>() {
            @Override
            public boolean filter(Event event) throws Exception {
                return event.value == 1;
            }
        }).next("middle").where(new SimpleCondition<Event>() {
            @Override
            public boolean filter(Event value) throws Exception {
                return value.value > 1;
            }
        }).followedBy("end").where(new SimpleCondition<Event>() {
            @Override
            public boolean filter(Event value) throws Exception {
                return value.value == 3;
            }
        });

        PatternStream<Event> patternStream = CEP.pattern(stream, pattern);

        patternStream.process(new PatternProcessFunction<Event, String>() {

            @Override
            public void processMatch(Map<String, List<Event>> match,
                                     Context ctx,
                                     Collector<String> out) throws Exception {

                Set<Map.Entry<String, List<Event>>> entries = match.entrySet();

                for (Map.Entry<String, List<Event>> entry : entries) {

                    entry.getValue().forEach(e -> {
                        out.collect(entry.getKey() + " " + e.name+" "+e.value);
                    });
                }
            }
        }).print();

        env.execute();
    }


}

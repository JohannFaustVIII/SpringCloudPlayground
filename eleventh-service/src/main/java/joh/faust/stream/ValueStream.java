package joh.faust.stream;

import joh.faust.stream.data.RandomValue;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
public class ValueStream {

    public static final String OUTPUT_TOPIC_NAME = "even-values";
    public static final String INPUT_TOPIC_NAME = "values";
    private static final Logger logger = LoggerFactory.getLogger(ValueStream.class);

    @Bean
    public KStream<String, RandomValue> evenValuesStream(StreamsBuilder kstreamBuilder) {
        KStream<String, RandomValue> input = kstreamBuilder.stream(INPUT_TOPIC_NAME);
        KStream<String, RandomValue> output = input
                .filter((key, value) -> key.equals("Even"))
                .map((key, value) -> {
                    logger.info("Mapping key=" + key +";value=" + value);
                    return new KeyValue<>(key, new RandomValue(value.getValue(), -1));
                });
        output.to(OUTPUT_TOPIC_NAME);
        return output;
    }

}

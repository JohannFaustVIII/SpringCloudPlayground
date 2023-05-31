package joh.faust.stream;

import joh.faust.stream.data.RandomValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class ValueConsumer {

    public static final String OUTPUT_TOPIC_NAME = "even-values";
    private static final Logger logger = LoggerFactory.getLogger(ValueConsumer.class);

    @KafkaListener(topics = OUTPUT_TOPIC_NAME)
    public void consume(RandomValue value) {
        logger.info("Received value: " + value);
    }
}

package joh.faust.stream;

import joh.faust.stream.data.RandomValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ValuePublisher {

    private static final String INPUT_TOPIC_NAME = "values";
    private static final Logger logger = LoggerFactory.getLogger(ValuePublisher.class);

    private final KafkaTemplate<String, RandomValue> kafkaTemplate;

    public ValuePublisher(KafkaTemplate<String, RandomValue> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(RandomValue randomValue) {
        String s = randomValue.getValue() % 2 == 0 ? "Even" : "Odd";
        logger.info("Publishing key: " + s + " with value: " + randomValue);
        kafkaTemplate.send(INPUT_TOPIC_NAME, s, randomValue);
    }
}

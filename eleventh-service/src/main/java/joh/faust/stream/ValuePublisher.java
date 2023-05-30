package joh.faust.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ValuePublisher {

    private static final String INPUT_TOPIC_NAME = "values";
    private static final Logger logger = LoggerFactory.getLogger(ValuePublisher.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ValuePublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(int i) {
        String s = i % 2 == 0 ? "Even" : "Odd";
        logger.info("Publishing key: " + s + " with value: " + i);
        kafkaTemplate.send(INPUT_TOPIC_NAME, s, String.valueOf(i));
    }
}

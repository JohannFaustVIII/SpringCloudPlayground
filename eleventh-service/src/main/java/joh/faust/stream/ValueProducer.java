package joh.faust.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ValueProducer {

    private static final Logger logger = LoggerFactory.getLogger(ValueProducer.class);
    private final ValuePublisher publisher;

    public ValueProducer(ValuePublisher publisher) {
        this.publisher = publisher;
    }

    @Scheduled(fixedRate = 2000)
    public void produceIntValue() {
        Random random = new Random();
        int value = random.nextInt(1000);
        logger.info("Generated value: " + value);
        publisher.produce(value);
    }
}

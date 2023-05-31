package joh.faust.stream.data;

import org.apache.kafka.common.serialization.Serdes;

public class RandomValueSerde extends Serdes.WrapperSerde<RandomValue> {


    public RandomValueSerde() {
        super(new RandomValueSerializer(), new RandomValueDeserializer());
    }
}

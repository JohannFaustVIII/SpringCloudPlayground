package joh.faust.stream.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class RandomValueSerializer implements Serializer<RandomValue> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, RandomValue data) {
        try {
            if (data == null){
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing RandomValue...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }
    }
}

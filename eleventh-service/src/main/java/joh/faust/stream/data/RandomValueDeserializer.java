package joh.faust.stream.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class RandomValueDeserializer implements Deserializer<RandomValue> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public RandomValue deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing RandomValue...");
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), RandomValue.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }
}

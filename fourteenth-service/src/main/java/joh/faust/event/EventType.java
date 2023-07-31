package joh.faust.event;

public interface EventType {

    String getType();

    Class getEventClass();

    default ActionEvent fromBytes(byte[] eventData) {
        return SerializerUtils.deserializeFromJsonBytes(eventData, getEventClass());
    }
}

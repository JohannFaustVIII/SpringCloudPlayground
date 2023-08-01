package joh.faust.event;

import joh.faust.event.post.PostEvent;
import joh.faust.event.post.PostEventType;
import joh.faust.event.user.UserEvent;
import joh.faust.event.user.UserEventType;

import java.util.function.BiFunction;

public enum AggregateType {

    POST_EVENT("POST_EVENT", PostEventType::toEvent, PostEvent.class),
    USER_EVENT("USER_EVENT", UserEventType::toEvent, UserEvent.class);

    private final String type;

    private final BiFunction<String, byte[], ActionEvent> toEvent;
    private final Class<? extends ActionEvent> eventClass;

    AggregateType(String type, BiFunction<String, byte[], ActionEvent> toEvent, Class<? extends ActionEvent> eventClass) {
        this.type = type;
        this.toEvent = toEvent;
        this.eventClass = eventClass;
    }

    public String getType() {
        return type;
    }

    public Class getEventClass() {
        return eventClass;
    }

    public ActionEvent toEvent(String eventType, byte[] dataBytes) {
        return toEvent.apply(eventType, dataBytes);
    }

    public static AggregateType getByClass(Class<? extends ActionEvent> eventClass) {
        for (AggregateType type : AggregateType.values()) {
            if (type.getEventClass().equals(eventClass)) {
                return type;
            }
        }
        throw new AggregateTypeNotFoundException();
    }

    public static AggregateType getByTypeName(String typeName) {
        for (AggregateType type : AggregateType.values()) {
            if (type.getType().equals(typeName)) {
                return type;
            }
        }
        throw new AggregateTypeNotFoundException();
    }

    static class AggregateTypeNotFoundException extends RuntimeException {

    }
}

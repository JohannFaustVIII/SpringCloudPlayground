package joh.faust.es.event;

import joh.faust.es.post.event.PostEvent;
import joh.faust.es.post.PostEventType;
import joh.faust.es.user.event.UserEvent;
import joh.faust.es.user.UserEventType;

import java.util.function.BiFunction;

public enum EventMetatype {

    POST_EVENT("POST_EVENT", PostEventType::toEvent, PostEvent.class),
    USER_EVENT("USER_EVENT", UserEventType::toEvent, UserEvent.class);

    private final String type;

    private final BiFunction<String, byte[], ActionEvent> toEvent;
    private final Class<? extends ActionEvent> eventClass;

    EventMetatype(String type, BiFunction<String, byte[], ActionEvent> toEvent, Class<? extends ActionEvent> eventClass) {
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

    public static EventMetatype getByClass(Class<? extends ActionEvent> eventClass) {
        for (EventMetatype type : EventMetatype.values()) {
            if (type.getEventClass().isAssignableFrom(eventClass)) {
                return type;
            }
        }
        throw new AggregateTypeNotFoundException();
    }

    public static EventMetatype getByTypeName(String typeName) {
        for (EventMetatype type : EventMetatype.values()) {
            if (type.getType().equals(typeName)) {
                return type;
            }
        }
        throw new AggregateTypeNotFoundException();
    }

    static class AggregateTypeNotFoundException extends RuntimeException {

    }
}

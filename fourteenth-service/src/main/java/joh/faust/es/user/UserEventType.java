package joh.faust.es.user;

import joh.faust.es.event.ActionEvent;
import joh.faust.es.event.EventType;
import joh.faust.es.event.SerializerUtils;
import joh.faust.es.user.event.UserCreatedEvent;
import joh.faust.es.user.event.UserEvent;

public enum UserEventType implements EventType {

    USER_CREATED_EVENT("USER_CREATED", UserCreatedEvent.class);

    private final String type;

    private final Class<? extends UserEvent> eventClass;

    UserEventType(String type, Class<? extends UserEvent> eventClass) {
        this.type = type;
        this.eventClass = eventClass;
    }

    @Override
    public String getType() {
        return type;
    }

    public Class getEventClass() {
        return eventClass;
    }

    public static ActionEvent toEvent(String typeName, byte[] bytes) {
        return UserEventType.getByTypeName(typeName).deserialize(bytes);
    }

    public static EventType getByClass(Class<? extends UserEvent> eventClass) {
        for (UserEventType type : UserEventType.values()) {
            if (type.getEventClass().isAssignableFrom(eventClass)) {
                return type;
            }
        }
        throw new UserEventTypeNotFoundException();
    }

    public static UserEventType getByTypeName(String typeName) {
        for (UserEventType type : UserEventType.values()) {
            if (type.getType().equals(typeName)) {
                return type;
            }
        }
        throw new UserEventTypeNotFoundException();
    }

    public UserEvent deserialize(byte[] bytes) {
        return SerializerUtils.deserializeFromJsonBytes(bytes, eventClass);
    }

    static class UserEventTypeNotFoundException extends RuntimeException {

    }
}

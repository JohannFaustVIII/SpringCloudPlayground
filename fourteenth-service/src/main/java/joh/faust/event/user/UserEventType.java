package joh.faust.event.user;

import joh.faust.event.EventType;

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

    public static EventType getByClass(Class<? extends UserEvent> eventClass) {
        for (UserEventType type : UserEventType.values()) {
            if (type.getEventClass().equals(eventClass)) {
                return type;
            }
        }
        throw new RuntimeException(); //TODO: Add proper exception
    }
}

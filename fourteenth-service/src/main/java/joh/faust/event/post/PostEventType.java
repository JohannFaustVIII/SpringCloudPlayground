package joh.faust.event.post;

import joh.faust.event.EventType;

public enum PostEventType implements EventType {

    POST_CREATED_EVENT("POST_CREATED", PostCreatedEvent.class),
    POST_REMOVED_EVENT("POST_REMOVED", PostRemovedEvent.class),
    POST_CONTENT_UPDATED("POST_CONTENT_UPDATED", PostContentUpdatedEvent.class),
    POST_NAME_UPDATED("POST_NAME_UPDATED", PostNameUpdatedEvent.class);

    private final String type;

    private final Class<? extends PostEvent> eventClass;

    PostEventType(String type, Class<? extends PostEvent> eventClass) {
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

    public static EventType getByClass(Class<? extends PostEvent> eventClass) {
        for (PostEventType type : PostEventType.values()) {
            if (type.getEventClass().equals(eventClass)) {
                return type;
            }
        }
        throw new RuntimeException(); //TODO: Add proper exception
    }
}

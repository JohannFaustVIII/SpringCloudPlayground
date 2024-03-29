package joh.faust.es.post;

import joh.faust.es.event.ActionEvent;
import joh.faust.es.event.EventType;
import joh.faust.es.event.SerializerUtils;
import joh.faust.es.post.event.*;

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

    public static ActionEvent toEvent(String typeName, byte[] bytes) {
        return PostEventType.getByTypeName(typeName).deserialize(bytes);
    }
    
    public static EventType getByClass(Class<? extends PostEvent> eventClass) {
        for (PostEventType type : PostEventType.values()) {
            if (type.getEventClass().isAssignableFrom(eventClass)) {
                return type;
            }
        }
        throw new PostEventTypeNotFoundException();
    }

    public static PostEventType getByTypeName(String typeName) {
        for (PostEventType type : PostEventType.values()) {
            if (type.getType().equals(typeName)) {
                return type;
            }
        }
        throw new PostEventTypeNotFoundException();
    }

    public PostEvent deserialize(byte[] bytes) {
        return SerializerUtils.deserializeFromJsonBytes(bytes, eventClass);
    }

    static class PostEventTypeNotFoundException extends RuntimeException {

    }
}

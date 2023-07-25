package joh.faust.event;

import joh.faust.event.post.PostEvent;
import joh.faust.event.user.UserEvent;

public enum AggregateType {

    POST_EVENT("POST_EVENT", PostEvent.class),
    USER_EVENT("USER_EVENT", UserEvent.class);

    private final String type;
    private final Class<? extends ActionEvent> eventClass;

    AggregateType(String type, Class<? extends ActionEvent> eventClass) {
        this.type = type;
        this.eventClass = eventClass;
    }

    public String getType() {
        return type;
    }

    public Class getEventClass() {
        return eventClass;
    }

    public static AggregateType getByClass(Class<? extends ActionEvent> eventClass) {
        for (AggregateType type : AggregateType.values()) {
            if (type.getEventClass().equals(eventClass)) {
                return type;
            }
        }
        throw new RuntimeException(); //TODO: Add proper exception
    }
}

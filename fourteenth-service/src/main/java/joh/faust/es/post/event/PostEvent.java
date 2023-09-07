package joh.faust.es.post.event;

import joh.faust.es.event.ActionEvent;
import joh.faust.es.event.EventMetatype;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class PostEvent extends ActionEvent {


    public PostEvent(UUID eventId, LocalDateTime created) {
        super(eventId, created);
    }

    public PostEvent() {
        super();
    }

    @Override
    public String getMetaType() {
        return EventMetatype.getByClass(getClass()).getType();
    }
}

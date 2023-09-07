package joh.faust.es.user.event;

import joh.faust.es.event.ActionEvent;
import joh.faust.es.event.EventMetatype;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class UserEvent extends ActionEvent {

    public UserEvent(UUID eventId, LocalDateTime created) {
        super(eventId, created);
    }

    public UserEvent() {
        super();
    }

    @Override
    public String getMetaType() {
        return EventMetatype.getByClass(getClass()).getType();
    }


}

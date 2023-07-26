package joh.faust.event.user;

import joh.faust.event.ActionEvent;
import joh.faust.event.AggregateType;

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
    public String getAggregateType() {
        return AggregateType.getByClass(getClass()).getType();
    }
}

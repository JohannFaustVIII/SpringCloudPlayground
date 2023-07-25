package joh.faust.event.user;

import joh.faust.event.ActionEvent;
import joh.faust.event.AggregateType;

public abstract class UserEvent extends ActionEvent {

    @Override
    public String getAggregateType() {
        return AggregateType.getByClass(getClass()).getType();
    }
}

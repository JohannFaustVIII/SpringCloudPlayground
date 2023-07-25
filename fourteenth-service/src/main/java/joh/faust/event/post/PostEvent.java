package joh.faust.event.post;

import joh.faust.event.ActionEvent;
import joh.faust.event.AggregateType;

public abstract class PostEvent extends ActionEvent {

    @Override
    public String getAggregateType() {
        return AggregateType.getByClass(getClass()).getType();
    }
}

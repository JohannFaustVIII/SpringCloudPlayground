package joh.faust.event;

import joh.faust.entity.Post; // I'm not sure if this is correct, what we want to use is not db entity, as it will be defined by events, nor model dto, as it is read only

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PostAggregate extends Aggregate{

    private final Map<UUID, Post> posts = new HashMap<>();

    protected PostAggregate() {
        super(AggregateType.POST_EVENT);
    }

    @Override
    public void when(ActionEvent event) {
        event.applyEvent(this);
    }

    //TODO: what does it need:
    // 1. handle all operations and produce proper events to save them in db (via repository)
    // 2. be reproducible from events from a database
    // how it should be created? constructor with injected repository, use it to read events and remake the state?

}

package joh.faust.event;

import joh.faust.event.post.PostEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// abstraction to be extended by each aggregate of entity in service
public abstract class Aggregate {

    protected final AggregateType aggregateType;
    protected long version = 0;
    protected final List<Event> changes = new ArrayList<>();

    protected Aggregate(AggregateType aggregateType) {
        this.aggregateType = aggregateType;
    }

    public abstract void when(ActionEvent event);

    public void load(List<Event> events) {
        events.forEach(event -> {
            validate(event);
            when(event.getEvent());

            //TODO: think: loading has to check versions, but generating events has to set proper version
            // and should loading add events to changes or not? if not, the aggregate is equal to being a snapshot
            // so why not to save it to db, and then load from db, instead of loading via events?
        });
    }

    public <T extends Aggregate> T getAggregate() {
        return (T) this;
    }

    private void validate(Event event) {
        if (Objects.isNull(event) || !aggregateType.getType().equals(event.getAggregateType())) {
            throw new InvalidEventException(event.toString());
        }
        if (this.version + 1 != event.getVersion()) {
            throw new InvalidVersionException(event.toString());
        }
    }

    static class InvalidEventException extends RuntimeException {

        public InvalidEventException(String object) {
            super(object);
        }
    }

    static class InvalidVersionException extends RuntimeException {

        public InvalidVersionException(String object) {
            super(object);
        }
    }
}

package joh.faust.es.source;

import joh.faust.es.entity.Event;
import joh.faust.es.event.ActionEvent;
import joh.faust.es.event.EventMetatype;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Aggregate {

    private final EventMetatype eventMetatype;
    private long version = 0;
    private final List<Event> changes = new ArrayList<>();

    protected Aggregate(EventMetatype eventMetatype) {
        this.eventMetatype = eventMetatype;
    }

    public abstract void when(ActionEvent event);

    public void load(Iterable<Event> events) {
        events.forEach(event -> {
            validate(event);
            when(event.getEvent());
            this.version = event.getVersion();
        });
    }

    public void applyEvent(ActionEvent event) {
        this.version++;
        changes.add(createEvent(event));
        when(event);
    }

    public <T extends Aggregate> T getAggregate() {
        return (T) this;
    }

    public EventMetatype getEventMetatype() {
        return this.eventMetatype;
    }

    public List<Event> getChanges() {
        return changes;
    }

    protected Event createEvent(ActionEvent event) {
        return Event.builder()
                .aggregateType(event.getMetaType())
                .version(this.version)
                .eventType(event.getEventType())
                .data(event.toBytes())
                .created(LocalDateTime.now())
                .build();
    }

    private void validate(Event event) {
        if (Objects.isNull(event) || !eventMetatype.getType().equals(event.getAggregateType())) {
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

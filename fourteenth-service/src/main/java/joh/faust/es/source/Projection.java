package joh.faust.es.source;

import joh.faust.es.entity.Event;
import joh.faust.es.event.ActionEvent;
import joh.faust.es.event.EventMetatype;

import java.util.Objects;

public abstract class Projection {

    private final EventMetatype eventMetatype;
    private long version = 0;

    protected Projection(EventMetatype eventMetatype) {
        this.eventMetatype = eventMetatype;
    }

    public void load(Iterable<Event> events) {
        events.forEach(event -> {
            validate(event);
            when(event.getEvent());
            this.version = event.getVersion();
        });
    }

    private void when(ActionEvent event) {
        event.applyEvent(this);
    }

    private void validate(Event event) {
        if (Objects.isNull(event) || !eventMetatype.getType().equals(event.getAggregateType())) {
            throw new InvalidEventException(event.toString());
        }
        if (this.version + 1 != event.getVersion()) {
            throw new InvalidVersionException(event.toString());
        }
    }
    public <T extends Projection> T getProjection() {
        return (T) this;
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

    public long getVersion() {
        return version;
    }

    public EventMetatype getEventMetatype() {
        return eventMetatype;
    }
}

package joh.faust.event;

import java.util.Objects;

public abstract class Projection {

    // TODO: it can be really similar to aggregate, is it worth making?
    //  as for CQRS - yes, but for KISS - it doesn't look exactly right
    //  TO THINK FURTHER

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
}

package joh.faust.es.event;


import com.fasterxml.jackson.annotation.JsonIgnore;
import joh.faust.es.source.Aggregate;
import joh.faust.es.source.Projection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class ActionEvent {

    private final UUID eventId;
    private final LocalDateTime created;

    public ActionEvent() {
        this(UUID.randomUUID(), LocalDateTime.now());
    }

    public ActionEvent(UUID eventId, LocalDateTime created) {
        this.eventId = eventId;
        this.created = created;
    }

    @JsonIgnore
    public abstract String getMetaType();

    @JsonIgnore
    public abstract String getEventType();

    public abstract void applyEvent(Aggregate aggregate);

    public abstract void applyEvent(Projection projection);

    public byte[] toBytes() {
        return SerializerUtils.serializeToJsonBytes(this);
    }


}

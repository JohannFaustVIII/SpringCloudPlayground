package joh.faust.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

// event to work as dto to db or event queue
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    // TODO: below should be in event entity? maybe
    // TODO: how to handle successful insert/update/delete? Transactional and then create event if successful?
    // TODO: the idea has changed: keep the state of a single object as aggregate, apply changes by generating events,
    // TODO: increase versions with each change, save snapshots to db to keep reloading easier, and reapply events when
    // TODO: reload from the db
    // TODO: learn about projection and make the whole idea KISS

    public UUID id;
    private String aggregateId;
    private String eventType;
    private String aggregateType;
    private long version;
    private byte[] data;
    public LocalDateTime created;

    public Event(String eventType, String aggregateType) {
        this.id = UUID.randomUUID();
        this.eventType = eventType;
        this.aggregateType = aggregateType;
        this.created = LocalDateTime.now();
    }
}

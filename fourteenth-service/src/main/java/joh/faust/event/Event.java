package joh.faust.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    private UUID id;
    private String aggregateId;
    private String eventType;
    private String aggregateType;
    private long version;
    private byte[] data;
    private LocalDateTime created;

    public Event(String eventType, String aggregateType, long version, byte[] data) {
        this.id = UUID.randomUUID();
        this.eventType = eventType;
        this.aggregateType = aggregateType;
        this.version = version;
        this.created = LocalDateTime.now();
    }

    // TODO: WIP, just to see how it could be implemented
    public ActionEvent getEvent() {
        AggregateType aggregateType = AggregateType.getByTypeName(this.aggregateType);
        return aggregateType.toEvent(this.eventType, this.data);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", aggregateId='" + aggregateId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", aggregateType='" + aggregateType + '\'' +
                ", version=" + version +
                ", data=" + Arrays.toString(data) +
                ", created=" + created +
                '}';
    }
}

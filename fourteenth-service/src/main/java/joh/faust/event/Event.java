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

    // TODO: learn about projection and make the whole idea KISS
    // TODO: now think about projection - aggregate generates event and handler is supposed to send it to db
    // TODO: soo projection should always read all events from db and recreate state?
    // TODO: might be better to listen to changes (more like idea to use with message queue)
    // TODO: or apply only events after the given version of current projection (and that could work with db)

    private UUID id;
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

    public ActionEvent getEvent() {
        EventMetatype eventMetatype = EventMetatype.getByTypeName(this.aggregateType);
        return eventMetatype.toEvent(this.eventType, this.data);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", aggregateType='" + aggregateType + '\'' +
                ", version=" + version +
                ", data=" + Arrays.toString(data) +
                ", created=" + created +
                '}';
    }
}

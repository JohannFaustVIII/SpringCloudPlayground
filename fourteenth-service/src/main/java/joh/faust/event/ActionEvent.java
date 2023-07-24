package joh.faust.event;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

// abstraction for internal use, to be extended by each event in the service
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
}

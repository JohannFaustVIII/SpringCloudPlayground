package joh.faust.event.user;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class UserCreatedEvent extends UserEvent {

    private final UUID newUserId;
    private final String userName;

    public UserCreatedEvent(UUID newUserId, String userName) {
        this.newUserId = newUserId;
        this.userName = userName;
    }

    public UserCreatedEvent(UUID eventId, LocalDateTime created, UUID newUserId, String userName) {
        super(eventId, created);
        this.newUserId = newUserId;
        this.userName = userName;
    }
}
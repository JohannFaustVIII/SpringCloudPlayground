package joh.faust.event.user;

import joh.faust.event.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class UserCreatedEvent extends UserEvent {

    private final UUID newUserId;
    private final String userName;

    public UserCreatedEvent(UUID newUserId, String userName) {
        super();
        this.newUserId = newUserId;
        this.userName = userName;
    }

    public UserCreatedEvent(UUID eventId, LocalDateTime created, UUID newUserId, String userName) {
        super(eventId, created);
        this.newUserId = newUserId;
        this.userName = userName;
    }

    @Override
    public String getEventType() {
        return UserEventType.getByClass(getClass()).getType();
    }

    @Override
    public void applyEvent(Aggregate aggregate) {
        UserAggregate userAggregate = aggregate.getAggregate();
        userAggregate.saveUser(
                new User(this.newUserId, userName)
        );
    }
}

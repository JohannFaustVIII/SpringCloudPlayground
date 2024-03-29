package joh.faust.es.user.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import joh.faust.es.source.Aggregate;
import joh.faust.es.source.Projection;
import joh.faust.es.user.User;
import joh.faust.es.user.command.UserAggregate;
import joh.faust.es.user.UserEventType;
import joh.faust.es.user.query.UserProjection;
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

    public UserCreatedEvent(@JsonProperty("eventId") UUID eventId, @JsonProperty("created") LocalDateTime created, @JsonProperty("newUserId") UUID newUserId, @JsonProperty("userName") String userName) {
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

    @Override
    public void applyEvent(Projection projection) {
        UserProjection userProjection = projection.getProjection();
        userProjection.saveUser(
                new User(this.newUserId, userName)
        );
    }
}

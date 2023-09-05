package joh.faust.event.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import joh.faust.event.Aggregate;
import joh.faust.event.Projection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class PostRemovedEvent extends PostEvent {

    private final UUID removedPostId;

    public PostRemovedEvent(UUID removedPostId) {
        this.removedPostId = removedPostId;
    }

    public PostRemovedEvent(@JsonProperty("eventId") UUID eventId, @JsonProperty("created") LocalDateTime created, @JsonProperty("removedPostId") UUID removedPostId) {
        super(eventId, created);
        this.removedPostId = removedPostId;
    }

    @Override
    public String eventType() {
        return PostEventType.getByClass(getClass()).getType();
    }

    @Override
    public void applyEvent(Aggregate aggregate) {
        PostAggregate postAggregate = aggregate.getAggregate();
        postAggregate.removePost(this.removedPostId);
    }

    @Override
    public void applyEvent(Projection projection) {
        PostProjection postProjection = projection.getProjection();
        postProjection.removePost(this.removedPostId);

    }
}

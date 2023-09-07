package joh.faust.es.post.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import joh.faust.es.source.Aggregate;
import joh.faust.es.source.Projection;
import joh.faust.es.post.command.PostAggregate;
import joh.faust.es.post.PostEventType;
import joh.faust.es.post.query.PostProjection;
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
    public String getEventType() {
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

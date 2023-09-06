package joh.faust.event.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import joh.faust.event.Aggregate;
import joh.faust.event.Projection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class PostCreatedEvent extends PostEvent {

    private final UUID newPostId;
    private final UUID creatorId;
    private final String postName;
    private final String postContent;

    public PostCreatedEvent(UUID newPostId, UUID creatorId, String postName, String postContent) {
        super();
        this.newPostId = newPostId;
        this.creatorId = creatorId;
        this.postName = postName;
        this.postContent = postContent;
    }

    public PostCreatedEvent(@JsonProperty("eventId") UUID eventId, @JsonProperty("created") LocalDateTime created, @JsonProperty("newPostId") UUID newPostId, @JsonProperty("creatorId") UUID creatorId, @JsonProperty("postName") String postName, @JsonProperty("postContent") String postContent) {
        super(eventId, created);
        this.newPostId = newPostId;
        this.creatorId = creatorId;
        this.postName = postName;
        this.postContent = postContent;
    }

    @Override
    public String getEventType() {
        return PostEventType.getByClass(getClass()).getType();
    }

    @Override
    public void applyEvent(Aggregate aggregate) {
        PostAggregate postAggregate = aggregate.getAggregate();
        postAggregate.savePost(
                new Post(this.newPostId, this.postName, this.postContent, this.creatorId)
        );
    }

    @Override
    public void applyEvent(Projection projection) {
        PostProjection postProjection = projection.getProjection();
        postProjection.savePost(
                new Post(this.newPostId, this.postName, this.postContent, this.creatorId)
        );
    }
}

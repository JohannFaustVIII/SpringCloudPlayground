package joh.faust.es.post.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import joh.faust.es.source.Aggregate;
import joh.faust.es.source.Projection;
import joh.faust.es.post.Post;
import joh.faust.es.post.command.PostAggregate;
import joh.faust.es.post.PostEventType;
import joh.faust.es.post.query.PostProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class PostContentUpdatedEvent extends PostEvent {

    private final UUID postId;
    private final String newContent;

    public PostContentUpdatedEvent(UUID postId, String newContent) {
        super();
        this.postId = postId;
        this.newContent = newContent;
    }

    public PostContentUpdatedEvent(@JsonProperty("eventId") UUID eventId, @JsonProperty("created") LocalDateTime created, @JsonProperty("postId") UUID postId, @JsonProperty("newContent") String newContent) {
        super(eventId, created);
        this.postId = postId;
        this.newContent = newContent;
    }

    @Override
    public String getEventType() {
        return PostEventType.getByClass(getClass()).getType();
    }

    @Override
    public void applyEvent(Aggregate aggregate) {
        PostAggregate postAggregate = aggregate.getAggregate();
        Post post = postAggregate.getPost(this.postId);
        post.setPostContent(this.newContent);
        postAggregate.savePost(post);
    }

    @Override
    public void applyEvent(Projection projection) {
        PostProjection postProjection = projection.getProjection();
        Post post = postProjection.findById(this.postId).orElseThrow();
        post.setPostContent(this.newContent);
        postProjection.savePost(post);
    }
}

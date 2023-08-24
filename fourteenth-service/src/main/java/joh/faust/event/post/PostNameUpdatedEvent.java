package joh.faust.event.post;

import joh.faust.event.Aggregate;
import joh.faust.event.Projection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class PostNameUpdatedEvent extends PostEvent {

    private final UUID postId;
    private final String newName;

    public PostNameUpdatedEvent(UUID postId, String newName) {
        this.postId = postId;
        this.newName = newName;
    }

    public PostNameUpdatedEvent(UUID eventId, LocalDateTime created, UUID postId, String newName) {
        super(eventId, created);
        this.postId = postId;
        this.newName = newName;
    }

    @Override
    public String getEventType() {
        return PostEventType.getByClass(getClass()).getType();
    }

    @Override
    public void applyEvent(Aggregate aggregate) {
        PostAggregate postAggregate = aggregate.getAggregate();
        Post post = postAggregate.getPost(this.postId);
        post.setPostName(newName);
        postAggregate.savePost(post);
    }

    @Override
    public void applyEvent(Projection projection) {
        PostProjection postProjection = projection.getProjection();
        Post post = postProjection.findById(this.postId).orElseThrow();
        post.setPostName(newName);
        postProjection.savePost(post);
    }
}

package joh.faust.event.post;

import joh.faust.event.Aggregate;
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

    public PostContentUpdatedEvent(UUID eventId, LocalDateTime created, UUID postId, String newContent) {
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
}

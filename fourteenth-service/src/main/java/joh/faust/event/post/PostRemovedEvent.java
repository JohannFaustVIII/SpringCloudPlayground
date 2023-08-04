package joh.faust.event.post;

import joh.faust.event.Aggregate;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class PostRemovedEvent extends PostEvent {

    private final UUID removedPostId;

    public PostRemovedEvent(UUID removedPostId) {
        this.removedPostId = removedPostId;
    }

    public PostRemovedEvent(UUID eventId, LocalDateTime created, UUID removedPostId) {
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
}

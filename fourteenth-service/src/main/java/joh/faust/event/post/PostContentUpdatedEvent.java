package joh.faust.event.post;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

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
}

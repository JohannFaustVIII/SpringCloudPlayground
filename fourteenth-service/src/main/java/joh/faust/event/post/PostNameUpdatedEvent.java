package joh.faust.event.post;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

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
}

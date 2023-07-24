package joh.faust.event.post;

import joh.faust.event.ActionEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class PostCreatedEvent extends ActionEvent {

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

    public PostCreatedEvent(UUID eventId, LocalDateTime created, UUID newPostId, UUID creatorId, String postName, String postContent) {
        super(eventId, created);
        this.newPostId = newPostId;
        this.creatorId = creatorId;
        this.postName = postName;
        this.postContent = postContent;
    }
}

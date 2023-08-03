package joh.faust.event.post;

import joh.faust.entity.Post;
import joh.faust.event.Aggregate;
import joh.faust.event.PostAggregate;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
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

    public PostCreatedEvent(UUID eventId, LocalDateTime created, UUID newPostId, UUID creatorId, String postName, String postContent) {
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
        // make a class which doesn't need user to don't require to read data from aggregate via repository?
        // WIP
//        postAggregate.addPost(
//                new Post(this.newPostId, this.postName, this.postContent, postAggregate.getUser(creatorId))
//        );


    }
}

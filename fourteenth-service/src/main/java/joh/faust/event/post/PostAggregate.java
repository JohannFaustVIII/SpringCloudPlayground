package joh.faust.event.post;

import joh.faust.event.ActionEvent;
import joh.faust.event.Aggregate;
import joh.faust.event.AggregateType;
import joh.faust.event.EventRepository;
import joh.faust.event.user.UserAggregate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class PostAggregate extends Aggregate {

    private final Map<UUID, Post> posts = new HashMap<>();

    private final UserAggregate userAggregate;

    protected PostAggregate(EventRepository eventRepository) {
        super(AggregateType.POST_EVENT);
        this.userAggregate = new UserAggregate();
        this.userAggregate.load(eventRepository.findByAggregateType(this.userAggregate.getAggregateType().getType()));
        this.load(eventRepository.findByAggregateType(this.getAggregateType().getType())); // TODO: doesn't look good?
    }

    @Override
    public void when(ActionEvent event) {
        event.applyEvent(this);
    }

    void savePost(Post post) {
        posts.put(post.getPostId(), post);
    }

    Post getPost(UUID postId) {
        return posts.get(postId);
    }

    void removePost(UUID postId) {
        posts.remove(postId);
    }

    public void createPost(String name, String content, UUID creatorId) {
        Objects.requireNonNull(creatorId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(content);

        if (!userAggregate.existsUser(creatorId)) {
            throw new PostUnknownCreatorIdException();
        }

        UUID postId;
        do {
            postId = UUID.randomUUID();
        } while (posts.containsKey(postId));

        ActionEvent event = PostCreatedEvent.builder()
                .newPostId(postId)
                .creatorId(creatorId)
                .postName(name)
                .postContent(content)
                .build();

        applyEvent(event);
    }

    public void deletePost(UUID postId) {
        Objects.requireNonNull(postId);

        if (!posts.containsKey(postId)) {
            throw new PostUnknownIdException();
        }

        applyEvent(
                PostRemovedEvent.builder()
                .removedPostId(postId)
                .build()
        );
    }

    public void updatePostContent(UUID postId, String newContent) {
        Objects.requireNonNull(postId);
        Objects.requireNonNull(newContent);

        if (!posts.containsKey(postId)) {
            throw new PostUnknownIdException();
        }

        applyEvent(
                PostContentUpdatedEvent.builder()
                        .postId(postId)
                        .newContent(newContent)
                        .build()
        );
    }

    public void updatePostName(UUID postId, String newName) {
        Objects.requireNonNull(postId);
        Objects.requireNonNull(newName);

        if (!posts.containsKey(postId)) {
            throw new PostUnknownIdException();
        }

        applyEvent(
                PostNameUpdatedEvent.builder()
                        .postId(postId)
                        .newName(newName)
                        .build()
        );
    }

    private class PostUnknownCreatorIdException extends RuntimeException {
    }

    private class PostUnknownIdException extends RuntimeException {
    }

    //TODO: what does it need:
    // 1. handle all operations and produce proper events to save them in db (via repository)
    // 2. be reproducible from events from a database
    // how it should be created? constructor with injected repository, use it to read events and remake the state?

}

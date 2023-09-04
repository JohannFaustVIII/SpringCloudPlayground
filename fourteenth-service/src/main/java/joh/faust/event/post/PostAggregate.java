package joh.faust.event.post;

import joh.faust.event.ActionEvent;
import joh.faust.event.Aggregate;
import joh.faust.event.EventMetatype;
import joh.faust.event.user.UserAggregate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PostAggregate extends Aggregate {

    private final Map<UUID, Post> posts = new HashMap<>();

    private final UserAggregate userAggregate;

    public PostAggregate(UserAggregate userAggregate) {
        super(EventMetatype.POST_EVENT);
        this.userAggregate = userAggregate;
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
}

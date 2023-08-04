package joh.faust.event.post;

import joh.faust.event.ActionEvent;
import joh.faust.event.Aggregate;
import joh.faust.event.AggregateType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PostAggregate extends Aggregate {

    private final Map<UUID, Post> posts = new HashMap<>();

    protected PostAggregate() {
        super(AggregateType.POST_EVENT);
    }

    @Override
    public void when(ActionEvent event) {
        event.applyEvent(this);
    }

    void savePost(Post post) {
        posts.put(post.getPostId(), post);
    }

    public Post getPost(UUID postId) {
        return posts.get(postId);
    }

    public void removePost(UUID postId) {
        posts.remove(postId);
    }

    //TODO: what does it need:
    // 1. handle all operations and produce proper events to save them in db (via repository)
    // 2. be reproducible from events from a database
    // how it should be created? constructor with injected repository, use it to read events and remake the state?

}

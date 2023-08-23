package joh.faust.event.post;

import joh.faust.event.Projection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostProjection extends Projection {

    public List<Post> getAllPosts() {
        return null; // TODO: to implement
    }

    public Optional<Post> findById(UUID id) {
        return null; // TODO: to implement
    }

    public Optional<Post> findByName(String name) {
        return null; // TODO: to implement
    }
}

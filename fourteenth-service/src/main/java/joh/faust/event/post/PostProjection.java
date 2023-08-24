package joh.faust.event.post;

import joh.faust.event.Projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostProjection extends Projection {

    private final List<Post> posts = new ArrayList<>();

    public List<Post> getAllPosts() {
        return posts;
    }

    public Optional<Post> findById(UUID id) {
        return posts
                .stream()
                .filter(post -> id.equals(post.getPostId()))
                .findFirst();
    }

    public Optional<Post> findByName(String name) {
        return posts
                .stream()
                .filter(post -> name.equals(post.getPostName()))
                .findFirst();
    }

    public void savePost(Post post) {
        posts
                .stream()
                .filter(p -> post.getPostId().equals(p.getPostId()))
                .findFirst()
                .ifPresentOrElse(p -> {
                    posts.remove(p);
                    posts.add(post);
                }, () -> posts.add(post));
    }

    public void removePost(UUID removedPostId) {
        posts
                .stream()
                .filter(p -> removedPostId.equals(p.getPostId()))
                .findFirst()
                .ifPresent(posts::remove);
    }
}

package joh.faust.es.post.query;

import joh.faust.es.event.EventMetatype;
import joh.faust.es.source.Projection;
import joh.faust.es.post.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PostProjection extends Projection {

    private final List<Post> posts = new ArrayList<>();

    public PostProjection() {
        super(EventMetatype.POST_EVENT);
    }

    public List<Post> getAllPosts() {
        return posts;
    }

    public List<Post> findByCreatorId(UUID creatorId) {
        return posts
                .stream()
                .filter(post -> creatorId.equals(post.getPostId()))
                .collect(Collectors.toList());
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

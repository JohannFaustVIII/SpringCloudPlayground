package joh.faust.es.post.query;

import joh.faust.es.post.query.PostProjection;
import joh.faust.model.Post;
import joh.faust.query.model.PostById;
import joh.faust.query.model.PostByName;
import joh.faust.query.service.PostQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostQueryServiceImpl implements PostQueryService {

    private final ApplicationContext context;

    @Autowired
    public PostQueryServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public List<Post> findAll() {
        PostProjection postProjection = context.getBean(PostProjection.class);

        return postProjection.getAllPosts()
                .stream()
                .map(post -> new Post(post.getPostId(), post.getPostName(), post.getPostContent(), post.getCreatorId()))
                .collect(Collectors.toList());
    }

    @Override
    public Post findPostById(PostById query) {
        PostProjection postProjection = context.getBean(PostProjection.class);

        return postProjection.findById(query.getId())
                .map(post -> new Post(post.getPostId(), post.getPostName(), post.getPostContent(), post.getCreatorId()))
                .orElseThrow(PostNotFoundException::new);
    }

    @Override
    public Post findPostByName(PostByName query) {
        PostProjection postProjection = context.getBean(PostProjection.class);

        return postProjection.findByName(query.getName())
                .map(post -> new Post(post.getPostId(), post.getPostName(), post.getPostContent(), post.getCreatorId()))
                .orElseThrow(PostNotFoundException::new);
    }

    @ResponseStatus(reason = "Post ID not found.", code = HttpStatus.NOT_FOUND)
    static class PostNotFoundException extends RuntimeException {

    }
}

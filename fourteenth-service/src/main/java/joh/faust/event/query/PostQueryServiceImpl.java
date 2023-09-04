package joh.faust.event.query;

import joh.faust.event.post.PostProjection;
import joh.faust.model.Post;
import joh.faust.query.model.PostById;
import joh.faust.query.model.PostByName;
import joh.faust.query.service.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostQueryServiceImpl implements PostQueryService {

    private final ApplicationContext context; // TODO: to change? but need to load projection each time... to solve

    @Autowired
    public PostQueryServiceImpl(ApplicationContext context) {
        this.context = context; // FIXME: context is null, why?
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

    static class PostNotFoundException extends RuntimeException { // TODO: maybe add exception handler to return proper http code?

    }
}

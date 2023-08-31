package joh.faust.event.query;

import joh.faust.event.post.PostProjection;
import joh.faust.model.Post;
import joh.faust.query.model.PostById;
import joh.faust.query.model.PostByName;
import joh.faust.query.service.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {

    private ApplicationContext context; // TODO: to change? but need to load projection each time... to solve

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
                .orElseThrow(); // TODO: custom exception?
    }

    @Override
    public Post findPostByName(PostByName query) {
        PostProjection postProjection = context.getBean(PostProjection.class);

        return postProjection.findByName(query.getName())
                .map(post -> new Post(post.getPostId(), post.getPostName(), post.getPostContent(), post.getCreatorId()))
                .orElseThrow(); // TODO: custom exception?
    }
}

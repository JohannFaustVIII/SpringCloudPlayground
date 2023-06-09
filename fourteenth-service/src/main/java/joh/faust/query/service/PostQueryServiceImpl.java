package joh.faust.query.service;

import joh.faust.model.Post;
import joh.faust.query.model.PostById;
import joh.faust.query.model.PostByName;
import joh.faust.query.repository.PostReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class PostQueryServiceImpl implements PostQueryService {

    private final PostReadRepository repository;

    @Override
    public List<Post> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(joh.faust.model.Post::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Post findPostById(PostById query) {
        return repository
                .findById(query.getId())
                .map(Post::fromEntity)
                .orElseThrow(); // TODO: ADD EXCEPTION FOR THIS?
    }

    @Override
    public Post findPostByName(PostByName query) {
        return repository
                .findByName(query.getName())
                .map(Post::fromEntity)
                .orElseThrow();// TODO: ADD EXCEPTION FOR THIS?
    }
}

package joh.faust.query;

import joh.faust.model.Post;
import joh.faust.query.model.PostById;
import joh.faust.query.model.PostByName;
import joh.faust.query.service.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostControllerQuery {

    private final PostQueryService postService;

    @GetMapping
    public List<Post> getAll() {
        return postService.findAll();
    }

    // TODO: below searches can be done better I think

    @GetMapping("/id/{id}")
    public Post getById(@PathVariable("id") long id) {
        return postService.findPostById(new PostById(id));
    }

    @GetMapping("/name/{name}")
    public Post getByName(@PathVariable("name") String name) {
        return postService.findPostByName(new PostByName(name));
    }



}

package joh.faust.query;

import joh.faust.model.Post;
import joh.faust.query.model.PostById;
import joh.faust.query.model.PostByName;
import joh.faust.query.service.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostQueryController {

    private final PostQueryService postService;

    @GetMapping(params = {"id"})
    public Post getById(@RequestParam("id") UUID id) {
        return postService.findPostById(new PostById(id));
    }

    @GetMapping(params = {"name"})
    public Post getByName(@RequestParam("name") String name) {
        return postService.findPostByName(new PostByName(name));
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.findAll();
    }

}

package joh.faust.command;

import joh.faust.command.model.post.CreatePost;
import joh.faust.command.model.post.DeletePost;
import joh.faust.command.model.post.UpdatePostContent;
import joh.faust.command.model.post.UpdatePostName;
import joh.faust.command.service.PostCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostCommandController {

    private final PostCommandService postService;

    @PostMapping
    public void addPost(CreatePost post) {
        postService.createPost(post);
    }

    @DeleteMapping
    public void deletePost(DeletePost post) {
        postService.deletePost(post);
    }

    @PatchMapping("/content")
    public void updatePostContent(UpdatePostContent post) {
        postService.updatePostContent(post);
    }

    @PatchMapping("/name")
    public void updatePostName(UpdatePostName post) {
        postService.updatePostName(post);
    }

}

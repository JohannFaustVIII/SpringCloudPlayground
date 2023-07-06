package joh.faust.command;

import joh.faust.command.model.CreatePost;
import joh.faust.command.model.DeletePost;
import joh.faust.command.model.UpdatePostContent;
import joh.faust.command.model.UpdatePostName;
import joh.faust.command.service.PostCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostControllerCommand {

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

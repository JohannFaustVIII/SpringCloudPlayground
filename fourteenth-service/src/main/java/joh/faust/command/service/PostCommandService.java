package joh.faust.command.service;

import joh.faust.command.model.post.CreatePost;
import joh.faust.command.model.post.DeletePost;
import joh.faust.command.model.post.UpdatePostContent;
import joh.faust.command.model.post.UpdatePostName;
import org.springframework.stereotype.Service;

@Service
public interface PostCommandService {

    void createPost(CreatePost command);
    void updatePostName(UpdatePostName command);
    void updatePostContent(UpdatePostContent command);
    void deletePost(DeletePost command);

}

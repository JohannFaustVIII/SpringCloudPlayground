package joh.faust.command.service;

import joh.faust.command.model.CreatePost;
import joh.faust.command.model.DeletePost;
import joh.faust.command.model.UpdatePostContent;
import joh.faust.command.model.UpdatePostName;
import org.springframework.stereotype.Service;

@Service
public interface PostCommandService {

    void createPost(CreatePost command);
    void updatePostName(UpdatePostName command);
    void updatePostContent(UpdatePostContent command);
    void deletePost(DeletePost command);

}

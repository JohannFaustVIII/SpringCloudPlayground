package joh.faust.command.service;

import joh.faust.command.repository.UserWriteRepository;
import joh.faust.entity.Post;
import joh.faust.command.model.post.CreatePost;
import joh.faust.command.model.post.DeletePost;
import joh.faust.command.model.post.UpdatePostContent;
import joh.faust.command.model.post.UpdatePostName;
import joh.faust.command.repository.PostWriteRepository;
import joh.faust.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
//@Component
public class PostCommandServiceImpl implements PostCommandService {

    private final PostWriteRepository repository;

    private final UserWriteRepository userRepository;

    @Override
    public void createPost(CreatePost command) {
        User user = userRepository.findById(command.getCreatorId()).orElseThrow();
        Post post = new Post(command.getName(), command.getContent(), user);
        repository.save(post);
    }

    @Override
    public void updatePostName(UpdatePostName command) {
        Optional<Post> post = repository.findById(command.getId());
        Post updatedPost = post.map(p -> {
            p.setName(command.getName());
            return p;
        }).orElseThrow();
        repository.save(updatedPost);
    }

    @Override
    public void updatePostContent(UpdatePostContent command) {
        Optional<Post> post = repository.findById(command.getId());
        Post updatedPost = post.map(p -> {
            p.setContent(command.getContent());
            return p;
        }).orElseThrow();
        repository.save(updatedPost);
    }

    @Override
    public void deletePost(DeletePost command) {
        repository.deleteById(command.getId());
    }
}

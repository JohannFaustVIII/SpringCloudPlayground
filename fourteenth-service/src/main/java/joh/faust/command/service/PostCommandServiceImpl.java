package joh.faust.command.service;

import joh.faust.Post;
import joh.faust.command.model.CreatePost;
import joh.faust.command.model.DeletePost;
import joh.faust.command.model.UpdatePostContent;
import joh.faust.command.model.UpdatePostName;
import joh.faust.command.repository.PostWriteRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PostCommandServiceImpl implements PostCommandService {

    private final PostWriteRepository repository;

    @Override
    public void createPost(CreatePost command) {
        Post post = new Post(command.getName(), command.getContent());
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
            p.setName(command.getContent());
            return p;
        }).orElseThrow();
        repository.save(updatedPost);
    }

    @Override
    public void deletePost(DeletePost command) {
        repository.deleteById(command.getId());
    }
}

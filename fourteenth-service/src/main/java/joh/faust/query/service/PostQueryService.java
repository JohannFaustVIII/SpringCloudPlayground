package joh.faust.query.service;

import joh.faust.Post;
import joh.faust.query.model.PostById;
import joh.faust.query.model.PostByName;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostQueryService {

    List<Post> findAll();

    Post findPostById(PostById query);

    Post findPostByName(PostByName query);

}

package joh.faust.query.repository;

import joh.faust.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostReadRepository extends CrudRepository<Post, Long> {

    Optional<Post> findByName(String name);

}

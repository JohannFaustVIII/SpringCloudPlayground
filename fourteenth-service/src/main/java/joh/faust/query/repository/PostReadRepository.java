package joh.faust.query.repository;

import joh.faust.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostReadRepository extends CrudRepository<Post, UUID> {

    Optional<Post> findByName(String name);

}

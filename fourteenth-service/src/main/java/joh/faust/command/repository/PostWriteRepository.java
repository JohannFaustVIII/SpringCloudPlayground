package joh.faust.command.repository;

import joh.faust.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostWriteRepository extends CrudRepository<Post, Long> {
}

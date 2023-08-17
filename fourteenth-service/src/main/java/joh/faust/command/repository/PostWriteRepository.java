package joh.faust.command.repository;

import joh.faust.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostWriteRepository extends CrudRepository<Post, UUID> {
}

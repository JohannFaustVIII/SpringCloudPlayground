package joh.faust.command.repository;

import joh.faust.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWriteRepository extends CrudRepository<User, Long> {
}

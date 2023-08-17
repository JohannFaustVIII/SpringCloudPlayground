package joh.faust.query.repository;

import joh.faust.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserReadRepository extends CrudRepository<User, UUID> {
}

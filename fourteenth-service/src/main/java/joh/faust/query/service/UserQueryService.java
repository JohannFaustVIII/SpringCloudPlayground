package joh.faust.query.service;

import joh.faust.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserQueryService {

    List<User> findAll();

}

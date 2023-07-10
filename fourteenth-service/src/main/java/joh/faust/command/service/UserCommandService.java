package joh.faust.command.service;

import joh.faust.command.model.user.CreateUser;
import org.springframework.stereotype.Service;

@Service
public interface UserCommandService {

    void createUser(CreateUser command);
}

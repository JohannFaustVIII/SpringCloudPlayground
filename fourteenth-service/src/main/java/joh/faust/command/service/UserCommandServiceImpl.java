package joh.faust.command.service;

import joh.faust.command.model.user.CreateUser;
import joh.faust.command.repository.UserRepository;
import joh.faust.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository repository;

    @Override
    public void createUser(CreateUser command) {
        User user = new User(command.getName());
        repository.save(user);
    }
}

package joh.faust.command;

import joh.faust.command.model.user.CreateUser;
import joh.faust.command.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserCommandController {

    private final UserCommandService service;

    @PostMapping
    public void addUser(CreateUser command) {
        service.createUser(command);
    }

}

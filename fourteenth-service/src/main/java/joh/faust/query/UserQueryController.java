package joh.faust.query;

import joh.faust.model.User;
import joh.faust.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserQueryController {

    private final UserQueryService service;

    @GetMapping
    public List<User> getUsers() {
        return service.findAll();
    }
}

package joh.faust.event.query;

import joh.faust.event.user.UserProjection;
import joh.faust.model.User;
import joh.faust.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private ApplicationContext context; // TODO: to change? but need to load projection each time... to solve

    @Override
    public List<User> findAll() {
        UserProjection userProjection = context.getBean(UserProjection.class);
        return userProjection.getAllUsers()
                .stream()
                .map(user -> new User(user.getUserId(), user.getUserName(), Collections.emptyList())) // TODO: find posts!
                .collect(Collectors.toList());
    }
}

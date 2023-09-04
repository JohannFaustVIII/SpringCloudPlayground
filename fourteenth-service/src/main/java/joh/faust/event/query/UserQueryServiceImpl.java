package joh.faust.event.query;

import joh.faust.event.post.Post;
import joh.faust.event.post.PostProjection;
import joh.faust.event.user.UserProjection;
import joh.faust.model.User;
import joh.faust.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserQueryServiceImpl implements UserQueryService {

    private ApplicationContext context; // TODO: to change? but need to load projection each time... to solve

    @Autowired
    public UserQueryServiceImpl(ApplicationContext context) {
        this.context = context; // FIXME: context is null, why?
    }

    @Override
    public List<User> findAll() {
        UserProjection userProjection = context.getBean(UserProjection.class);
        PostProjection postProjection = context.getBean(PostProjection.class);

        return userProjection.getAllUsers()
                .stream()
                .map(user -> new User(user.getUserId(), user.getUserName(), postProjection
                        .findByCreatorId(user.getUserId())
                        .stream()
                        .map(Post::getPostId)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}

package joh.faust.es.user.query;

import joh.faust.es.event.EventMetatype;
import joh.faust.es.source.Projection;
import joh.faust.es.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserProjection extends Projection {

    private final List<User> users = new ArrayList<>();

    public UserProjection() {
        super(EventMetatype.USER_EVENT);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void saveUser(User user) {
        users.add(user);
    }
}

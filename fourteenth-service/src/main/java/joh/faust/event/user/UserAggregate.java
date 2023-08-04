package joh.faust.event.user;

import joh.faust.event.ActionEvent;
import joh.faust.event.Aggregate;
import joh.faust.event.AggregateType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserAggregate extends Aggregate {

    private final Map<UUID, User> users = new HashMap<>();

    protected UserAggregate() {
        super(AggregateType.USER_EVENT);
    }

    @Override
    public void when(ActionEvent event) {
        event.applyEvent(this);
    }

    public void saveUser(User user) {
        users.put(user.getUserId(), user);
    }
}

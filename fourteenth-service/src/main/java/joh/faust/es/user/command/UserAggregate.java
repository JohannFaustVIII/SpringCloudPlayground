package joh.faust.es.user.command;

import joh.faust.es.event.ActionEvent;
import joh.faust.es.source.Aggregate;
import joh.faust.es.event.EventMetatype;
import joh.faust.es.user.User;
import joh.faust.es.user.event.UserCreatedEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class UserAggregate extends Aggregate {

    private final Map<UUID, User> users = new HashMap<>();

    public UserAggregate() {
        super(EventMetatype.USER_EVENT);
    }

    @Override
    public void when(ActionEvent event) {
        event.applyEvent(this);
    }

    public void saveUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void createUser(String userName) {
        Objects.requireNonNull(userName);

        UUID userId;
        do {
            userId = UUID.randomUUID();
        } while (users.containsKey(userId));

        applyEvent(
                UserCreatedEvent.builder()
                        .newUserId(userId)
                        .userName(userName)
                        .build()
        );
    }

    public boolean existsUser(UUID creatorId) {
        return users.containsKey(creatorId);
    }
}

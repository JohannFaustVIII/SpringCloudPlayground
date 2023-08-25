package joh.faust.event.user;

import joh.faust.event.ActionEvent;
import joh.faust.event.Aggregate;
import joh.faust.event.EventMetatype;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class UserAggregate extends Aggregate {

    private final Map<UUID, User> users = new HashMap<>();

    public UserAggregate() {
        super(EventMetatype.USER_EVENT);
    }

    @Override
    public void when(ActionEvent event) {
        event.applyEvent(this);
    }

    void saveUser(User user) {
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

package joh.faust.event.user;

import joh.faust.event.ActionEvent;
import joh.faust.event.Aggregate;
import joh.faust.event.AggregateType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UserAggregate extends Aggregate {

    private final Map<UUID, User> users = new HashMap<>();

    protected UserAggregate() {
        super(AggregateType.USER_EVENT);
    }

    @Override
    public void when(ActionEvent event) {
        event.applyEvent(this);
    }

    void saveUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void createUser(String userName) {
        UUID userId = null;
        do {
            userId = UUID.randomUUID();
        } while (!users.containsKey(userId));
        version++; // TODO: this part need to be resolved, requires to increment version in each place
        ActionEvent event = new UserCreatedEvent(userId, userName);

        changes.add(createEvent(event));
        when(event);
    }
}

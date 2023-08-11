package joh.faust.event.command;

import joh.faust.command.model.user.CreateUser;
import joh.faust.command.service.UserCommandService;
import joh.faust.event.EventRepository;
import joh.faust.event.user.UserAggregate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// WIP
@Component
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final EventRepository eventRepository;
    private final UserAggregate userAggregate;

    @Override
    public void createUser(CreateUser command) {
        userAggregate.createUser(command.getName());

        eventRepository.saveAll(userAggregate.getChanges());
    }
}

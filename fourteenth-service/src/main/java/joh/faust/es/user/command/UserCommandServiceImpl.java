package joh.faust.es.user.command;

import joh.faust.command.model.user.CreateUser;
import joh.faust.command.service.UserCommandService;
import joh.faust.es.entity.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

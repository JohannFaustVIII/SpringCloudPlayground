package joh.faust.event.command;

import joh.faust.command.model.post.CreatePost;
import joh.faust.command.model.post.DeletePost;
import joh.faust.command.model.post.UpdatePostContent;
import joh.faust.command.model.post.UpdatePostName;
import joh.faust.command.service.PostCommandService;
import joh.faust.event.EventRepository;
import joh.faust.event.post.PostAggregate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

// WIP
@Component
@RequiredArgsConstructor
public class PostCommandServiceImpl implements PostCommandService {

    private final EventRepository eventRepository;
    private final PostAggregate postAggregate; // how to load it?

    @Override
    public void createPost(CreatePost command) {
        postAggregate.createPost(
                command.getName(),
                command.getContent(),
                UUID.fromString(command.getCreatorId().toString()) // TODO: CHANGE TYPE FROM LONG TO UUID
        );

        eventRepository.saveAll(postAggregate.getChanges()); // will it save only new or all? should add only new, and update existing
    }

    @Override
    public void updatePostName(UpdatePostName command) {
        postAggregate.updatePostName(
                UUID.fromString(command.getId().toString()), // TODO: CHANGE TYPE FROM LONG TO UUID
                command.getName()
        );

        eventRepository.saveAll(postAggregate.getChanges());
    }

    @Override
    public void updatePostContent(UpdatePostContent command) {
        postAggregate.updatePostContent(
                UUID.fromString(command.getId().toString()), // TODO: CHANGE TYPE FROM LONG TO UUID
                command.getContent()
        );

        eventRepository.saveAll(postAggregate.getChanges());
    }

    @Override
    public void deletePost(DeletePost command) {
        postAggregate.deletePost(
                UUID.fromString(command.getId().toString()) // TODO: CHANGE TYPE FROM LONG TO UUID
        );

        eventRepository.saveAll(postAggregate.getChanges());
    }
}

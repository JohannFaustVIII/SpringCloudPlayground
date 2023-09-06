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

@Component
@RequiredArgsConstructor
public class PostCommandServiceImpl implements PostCommandService {

    private final EventRepository eventRepository;
    private final PostAggregate postAggregate;

    @Override
    public void createPost(CreatePost command) {
        postAggregate.createPost(
                command.getName(),
                command.getContent(),
                command.getCreatorId()
        );

        eventRepository.saveAll(postAggregate.getChanges());
    }

    @Override
    public void updatePostName(UpdatePostName command) {
        postAggregate.updatePostName(
                command.getId(),
                command.getName()
        );

        eventRepository.saveAll(postAggregate.getChanges());
    }

    @Override
    public void updatePostContent(UpdatePostContent command) {
        postAggregate.updatePostContent(
                command.getId(),
                command.getContent()
        );

        eventRepository.saveAll(postAggregate.getChanges());
    }

    @Override
    public void deletePost(DeletePost command) {
        postAggregate.deletePost(
                command.getId()
        );

        eventRepository.saveAll(postAggregate.getChanges());
    }
}

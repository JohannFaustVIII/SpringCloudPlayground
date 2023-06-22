package joh.faust;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PostSaver {

    private PostRepository repository;

    public PostSaver(PostRepository repository) {
        this.repository = repository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        repository.save(new Post("My first post"));
    }
}

package joh.faust.event;

import joh.faust.event.post.PostProjection;
import joh.faust.event.user.UserProjection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// TODO: think if recreate fully, or to keep as singleton with updating on injection
@Configuration
public class ProjectionConfiguration {

    private final Object postProjectionLock = new Object();

    private PostProjection postProjection = null;

    private final Object userProjectionLock = new Object();

    private UserProjection userProjection = null;

    @Bean
    @Scope("prototype")
    public PostProjection postProjection(EventRepository eventRepository) {
        synchronized (postProjectionLock) {
            if (postProjection == null) {
                postProjection = new PostProjection();
                String aggregateType = postProjection.getEventMetatype().getType();
                Iterable<Event> events = eventRepository.findByAggregateType(aggregateType);
                postProjection.load(events);
            } else {
                String aggregateType = postProjection.getEventMetatype().getType();
                long version = postProjection.getVersion();
                Iterable<Event> events = eventRepository.findByAggregateTypeAndVersionGreaterThan(aggregateType, version);
                postProjection.load(events);
            }
            return postProjection;
        }
    }

    @Bean
    @Scope("prototype")
    public UserProjection userProjection(EventRepository eventRepository) {
        synchronized (userProjectionLock) {
            if (userProjection == null) {
                userProjection = new UserProjection();
                String aggregateType = userProjection.getEventMetatype().getType();
                Iterable<Event> events = eventRepository.findByAggregateType(aggregateType);
                userProjection.load(events);
            } else {
                String aggregateType = userProjection.getEventMetatype().getType();
                long version = userProjection.getVersion();
                Iterable<Event> events = eventRepository.findByAggregateTypeAndVersionGreaterThan(aggregateType, version);
                userProjection.load(events);
            }
            return userProjection;
        }
    }

}

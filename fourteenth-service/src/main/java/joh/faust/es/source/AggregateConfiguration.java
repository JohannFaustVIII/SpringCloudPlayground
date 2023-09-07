package joh.faust.es.source;

import joh.faust.es.entity.Event;
import joh.faust.es.entity.EventRepository;
import joh.faust.es.post.command.PostAggregate;
import joh.faust.es.user.command.UserAggregate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AggregateConfiguration {

    @Bean
    public PostAggregate postAggregate(EventRepository eventRepository, UserAggregate userAggregate) {
        PostAggregate postAggregate = new PostAggregate(userAggregate);
        String aggregateType = postAggregate.getEventMetatype().getType();
        Iterable<Event> events = eventRepository.findByAggregateType(aggregateType);
        postAggregate.load(events);
        return postAggregate;
    }

    @Bean
    public UserAggregate userAggregate(EventRepository eventRepository) {
        UserAggregate userAggregate = new UserAggregate();
        String aggregateType = userAggregate.getEventMetatype().getType();
        Iterable<Event> events = eventRepository.findByAggregateType(aggregateType);
        userAggregate.load(events);
        return userAggregate;
    }

}

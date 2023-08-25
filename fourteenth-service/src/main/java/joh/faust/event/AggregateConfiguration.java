package joh.faust.event;

import joh.faust.event.post.PostAggregate;
import joh.faust.event.user.UserAggregate;
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

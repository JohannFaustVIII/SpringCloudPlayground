package joh.faust.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {

    Iterable<Event> findByAggregateType(String aggregateType);

    Iterable<Event> findByEventType(String aggregateType);
}

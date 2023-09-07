package joh.faust.es.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {

    Iterable<Event> findByAggregateType(String aggregateType);

    Iterable<Event> findByAggregateTypeAndVersionGreaterThan(String aggregateType, long version); // to get events to update projections, to think if really required

    Iterable<Event> findByEventType(String aggregateType);
}

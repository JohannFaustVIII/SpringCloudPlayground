package joh.faust.event;

import java.util.Date;
import java.util.UUID;

public abstract class Event {

    // TODO: below should be in event entity? maybe
    // TODO: how to handle successful insert/update/delete? Transactional and then create event if successful?
    // TODO: the idea has changed: keep the state of a single object as aggregate, apply changes by generating events,
    // TODO: increase versions with each change, save snapshots to db to keep reloading easier, and reapply events when
    // TODO: reload from the db

    public final UUID id = UUID.randomUUID();
    public final Date created = new Date();

}

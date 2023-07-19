package joh.faust.event;

import java.util.Date;
import java.util.UUID;

public abstract class Event {

    // TODO: below should be in event entity? maybe
    // TODO: how to handle successful insert/update/delete? Transactional and then create event if successful?
    // TODO: ^^^ need to keep database consistency

    public final UUID id = UUID.randomUUID();
    public final Date created = new Date();

}

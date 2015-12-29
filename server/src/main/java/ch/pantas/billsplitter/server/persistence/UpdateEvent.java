package ch.pantas.billsplitter.server.persistence;

import ch.pantas.billsplitter.server.services.EventImporter;
import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import org.prevayler.Transaction;

import java.util.Date;
import java.util.UUID;

public class UpdateEvent implements Transaction<EventImporter> {
    private static final long serialVersionUID = -3956874425264258980L;

    private EventDto event;
    private UUID user;

    public UpdateEvent(EventDto event, UUID user) {
        this.event = event;
        this.user = user;
    }

    @Override
    public void executeOn(EventImporter system, Date executionTime) {
        system.updateEvent(event, user);
    }
}

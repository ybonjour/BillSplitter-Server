package ch.pantas.billsplitter.server.persistence;

import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import org.prevayler.Transaction;

import java.util.Date;

public class UpdateEvent implements Transaction<EventImporter> {
    private static final long serialVersionUID = -3956874425264258980L;

    private EventDto event;

    public UpdateEvent(EventDto event) {
        this.event = event;
    }

    @Override
    public void executeOn(EventImporter system, Date executionTime) {
        system.addEvent(event);
    }
}

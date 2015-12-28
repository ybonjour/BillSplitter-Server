package ch.pantas.billsplitter.server.services;

import ch.pantas.billsplitter.server.persistence.EventImporter;
import ch.pantas.billsplitter.server.persistence.UpdateEvent;
import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import ch.pantas.billsplitter.server.services.datatransfer.UserDto;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public class EventKeeper {
    private Prevayler<EventImporter> eventImporter;

    @Autowired
    public void setEventImporter(EventImporter eventImporter) {
        try {
            this.eventImporter = PrevaylerFactory.createPrevayler(eventImporter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEvent(EventDto event) {
        eventImporter.execute(new UpdateEvent(event));
    }

    public EventDto getEvent(UUID uuid) {
        return eventImporter.prevalentSystem().getEvent(uuid);
    }

    public Collection<EventDto> getEvents() {
        return eventImporter.prevalentSystem().getEvents();
    }

    public Collection<UserDto> getUsers() {
        return eventImporter.prevalentSystem().getUsers();
    }
}

package ch.pantas.billsplitter.server.persistence;

import ch.pantas.billsplitter.server.services.EventImporter;
import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import ch.pantas.billsplitter.server.services.datatransfer.UserDto;
import org.prevayler.Prevayler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public class EventKeeper {
    private Prevayler<EventImporter> eventImporter;

    @Autowired
    public EventKeeper(Prevayler<EventImporter> eventImporter) {
        this.eventImporter = eventImporter;
    }

    /*
     * Take a system snapshot every day at midnight.
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void takeSnapshot() {
        try {
            eventImporter.takeSnapshot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEvent(EventDto event, UUID user) {
        eventImporter.execute(new UpdateEvent(event, user));
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

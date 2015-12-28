package ch.pantas.billsplitter.server.persistence;

import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import ch.pantas.billsplitter.server.services.datatransfer.UserDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EventImporter {
    private ConcurrentHashMap<UUID, UserDto> users = new ConcurrentHashMap<>();
    private ConcurrentHashMap<UUID, EventDto> events = new ConcurrentHashMap<>();

    public void addEvent(EventDto event) {
        if (!users.contains(event.getOwner().getId())) {
            users.put(event.getOwner().getId(), event.getOwner());
        }
        events.put(event.getId(), event);
    }

    public EventDto getEvent(UUID uuid) {
        return events.get(uuid);
    }

    public Collection<EventDto> getEvents() {
        return events.values();
    }

    public Collection<UserDto> getUsers() {
        return users.values();
    }
}

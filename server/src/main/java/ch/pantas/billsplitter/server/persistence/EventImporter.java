package ch.pantas.billsplitter.server.persistence;

import ch.pantas.billsplitter.server.model.Event;
import ch.pantas.billsplitter.server.model.User;
import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import ch.pantas.billsplitter.server.services.datatransfer.UserDto;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class EventImporter implements Serializable {
    private static final long serialVersionUID = 3732183967711255299L;

    private ConcurrentHashMap<UUID, User> users = new ConcurrentHashMap<>();
    private ConcurrentHashMap<UUID, Event> events = new ConcurrentHashMap<>();

    public void addEvent(EventDto event) {
        User owner = users.get(event.getOwner().getId());
        if (owner == null) {
            owner = new User(event.getOwner());
            users.put(event.getOwner().getId(), owner);
        }
        events.put(event.getId(), new Event(event, owner));
    }

    public EventDto getEvent(UUID uuid) {
        return new EventDto(events.get(uuid));
    }

    public Collection<EventDto> getEvents() {
        return events.values().stream()
                .map(EventDto::new)
                .collect(Collectors.toList());
    }

    public Collection<UserDto> getUsers() {
        return users.values().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}

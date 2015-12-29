package ch.pantas.billsplitter.server.services;

import ch.pantas.billsplitter.server.model.Event;
import ch.pantas.billsplitter.server.model.Expense;
import ch.pantas.billsplitter.server.model.User;
import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import ch.pantas.billsplitter.server.services.datatransfer.ExpenseDto;
import ch.pantas.billsplitter.server.services.datatransfer.UserDto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EventImporter implements Serializable {
    private static final long serialVersionUID = 3732183967711255299L;

    private ConcurrentHashMap<UUID, User> users = new ConcurrentHashMap<>();
    private ConcurrentHashMap<UUID, Event> events = new ConcurrentHashMap<>();

    public void updateEvent(EventDto event, UUID user) {
        User owner = addOrGetUser(event.getOwner(), user);

        List<User> participants = event.getParticipants().stream()
                .map(u -> addOrGetUser(u, user))
                .collect(Collectors.toList());

        List<Expense> expenses = event.getExpenses().stream()
                .map(e -> createExpense(e))
                .collect(Collectors.toList());

        Event updatedEvent = new Event(event.getId(),
                event.getDescription(), event.getCurrency(), owner,
                participants, expenses);

        Event existingEvent = events.get(event.getId());
        if (existingEvent != null) {
            updatedEvent = EventMerger.merge(existingEvent, updatedEvent, user);
        }

        events.put(event.getId(), updatedEvent);
    }

    private Expense createExpense(ExpenseDto eventDto) {
        List<User> attendees = eventDto.getAttendees().stream()
                .map(e -> users.get(e))
                .collect(Collectors.toList());
        return new Expense(eventDto.getId(), users.get(eventDto.getPayer()), eventDto.getDescription(),
                eventDto.getAmount(), users.get(eventDto.getOwner()), attendees);
    }

    private User addOrGetUser(UserDto userDto, UUID updatingUser) {
        User user = users.get(userDto.getId());
        if (user == null) {
            user = new User(userDto.getId(), userDto.getName());
            users.put(user.getId(), user);
        }
        if (user.getId().equals(updatingUser)) {
            user.setName(userDto.getName());
        }
        return user;
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

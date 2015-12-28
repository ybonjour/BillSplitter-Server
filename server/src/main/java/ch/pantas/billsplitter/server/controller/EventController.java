package ch.pantas.billsplitter.server.controller;

import ch.pantas.billsplitter.server.model.Event;
import ch.pantas.billsplitter.server.model.User;
import ch.pantas.billsplitter.server.services.EventKeeper;
import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import ch.pantas.billsplitter.server.services.datatransfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventKeeper eventKeeper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<EventDto> listEvents() {
        return eventKeeper.getEvents();
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public EventDto getEvent(@PathVariable String eventId) {
        return eventKeeper.getEvent(UUID.fromString(eventId));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void updateEvent(@RequestBody EventDto event) {
        eventKeeper.updateEvent(event);
    }

    // Temporary function to have some data to play around with.
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String generateEvent() {

        Collection<UserDto> users = eventKeeper.getUsers();
        User owner = new User(UUID.randomUUID(), "dieter kruse");
        User hans = new User(UUID.randomUUID(), "hans müller");
        if (users.size() > 2) {
            Iterator<UserDto> iterator = users.iterator();
            owner = new User(iterator.next());
            hans = new User(iterator.next());
        }

        UUID uuid = UUID.randomUUID();
        Event event = new Event(uuid, "test_desc", "CHF", owner);
        event.setParticipants(Arrays.asList(owner, hans));
        EventDto eventDto = new EventDto(event);

        eventKeeper.updateEvent(eventDto);

        return uuid.toString();
    }
}
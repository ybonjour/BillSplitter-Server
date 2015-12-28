package ch.pantas.billsplitter.server.controller;

import ch.pantas.billsplitter.server.model.Event;
import ch.pantas.billsplitter.server.model.User;
import ch.pantas.billsplitter.server.services.EventKeeper;
import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

        User owner = new User(UUID.randomUUID(), "test_user");
        UUID uuid = UUID.randomUUID();
        EventDto event = new EventDto(new Event(uuid, "test_desc", "CHF", owner));

        eventKeeper.updateEvent(event);

        return uuid.toString();
    }
}
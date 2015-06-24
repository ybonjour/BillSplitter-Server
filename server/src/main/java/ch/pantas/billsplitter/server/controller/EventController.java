package ch.pantas.billsplitter.server.controller;

import ch.pantas.billsplitter.server.model.Event;
import ch.pantas.billsplitter.server.model.User;
import ch.pantas.billsplitter.server.services.EventImporter;
import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import ch.pantas.billsplitter.server.store.EventStore;
import ch.pantas.billsplitter.server.store.UserStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.acl.Owner;
import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventStore eventStore;

    @Autowired
    private UserStore userStore;

    @Autowired
    EventImporter eventImporter;

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    @ResponseBody
    public EventDto getEvent(@PathVariable String eventId) {

        UUID eventUuid = UUID.fromString(eventId);
        Event event = eventStore.findOne(eventUuid);

        EventDto eventDto = new EventDto(event);

        return eventDto;
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateEvent(@PathVariable EventDto event) {

        eventImporter.load(event);

        return "not implemented";
    }

    // Temporary function to have some data to play around with.
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    @ResponseBody
    public String generateEvent() {

        User owner = new User(UUID.randomUUID(), "test_user");
        UUID uuid = UUID.randomUUID();
        Event event = new Event(uuid, "test_desc", "CHF", owner);

        userStore.save(owner);
        eventStore.save(event);

        return uuid.toString();
    }
}
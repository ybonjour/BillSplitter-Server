package ch.pantas.billsplitter.server.controller;

import ch.pantas.billsplitter.server.model.Event;
import ch.pantas.billsplitter.server.services.EventImporter;
import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import ch.pantas.billsplitter.server.store.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventStore eventStore;

    @Autowired
    EventImporter eventImporter;

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    @ResponseBody
    public EventDto getEvent(@PathVariable String eventId) {

        UUID eventUuid = UUID.fromString(eventId);
        Event event = eventStore.findOne(eventUuid);

        EventDto eventDto = new EventDto();

        return eventDto;
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateEvent(@PathVariable EventDto event) {

        eventImporter.load(event);

        return "not implemented";
    }
}
package ch.pantas.billsplitter.server.services.datatransfer;

import ch.pantas.billsplitter.server.model.Event;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class EventDto implements Serializable {
    private static final long serialVersionUID = -6739250520070216200L;

    private UUID id;
    private String description;
    private String currency;
    private UserDto owner;
    private List<UserDto> participants;

    public EventDto() {
    }

    public EventDto(Event event) {
        this.id = event.getId();
        this.description = event.getDescription();
        this.currency = event.getCurrency();
        this.owner = new UserDto(event.getOwner());
        participants = event.getParticipants().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrency() {
        return currency;
    }

    public UserDto getOwner() {
        return owner;
    }

    public List<UserDto> getParticipants() {
        return participants;
    }
}

package ch.pantas.billsplitter.server.services.datatransfer;

import ch.pantas.billsplitter.server.model.Event;

import java.io.Serializable;
import java.util.UUID;

public class EventDto implements Serializable {
    private static final long serialVersionUID = -6739250520070216200L;

    private UUID id;
    private String description;
    private String currency;
    private UserDto owner;

    private EventDto() {
    }

    public EventDto(Event event) {
        this.id = event.getId();
        this.description = event.getDescription();
        this.currency = event.getCurrency();
        this.owner = new UserDto(event.getOwner());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }
}

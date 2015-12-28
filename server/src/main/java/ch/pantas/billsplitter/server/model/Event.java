package ch.pantas.billsplitter.server.model;

import ch.pantas.billsplitter.server.services.datatransfer.EventDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Event implements Serializable {
    private static final long serialVersionUID = -8979419621719034542L;

    private UUID id;
    private String description;
    private String currency;
    private User owner;
    private List<User> participants;

    protected Event(){ }

    public Event(UUID id, String description, String currency, User owner) {
        this.id = id;
        this.description = description;
        this.currency = currency;
        this.owner = owner;
    }

    public Event(EventDto event, User owner) {
        id = event.getId();
        description = event.getDescription();
        currency = event.getCurrency();
        this.owner = owner;
        this.participants = event.getParticipants().stream()
                .map(User::new)
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

    public User getOwner() {
        return owner;
    }

    public synchronized void setParticipants(Collection<User> participants) {
        this.participants = new ArrayList<>(participants);
    }

    public Collection<User> getParticipants() {
        return participants;
    }
}

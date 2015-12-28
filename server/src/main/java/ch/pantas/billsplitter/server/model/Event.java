package ch.pantas.billsplitter.server.model;

import java.io.Serializable;
import java.util.UUID;

public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String description;
    private String currency;
    private User owner;

    protected Event(){ }

    public Event(UUID id, String description, String currency, User owner) {
        this.id = id;
        this.description = description;
        this.currency = currency;
        this.owner = owner;
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
}

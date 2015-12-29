package ch.pantas.billsplitter.server.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

public class Expense implements Serializable {
    private static final long serialVersionUID = -2466768526207644050L;

    private UUID id;
    private User payer;
    private String description;
    private int amount;
    private User owner;
    private Collection<User> attendees;

    public Expense() {
    }

    public Expense(UUID id, User payer, String description, int amount, User owner, Collection<User> attendees) {
        this.id = id;
        this.payer = payer;
        this.description = description;
        this.amount = amount;
        this.owner = owner;
        this.attendees = attendees;
    }

    public UUID getId() {
        return id;
    }

    public User getPayer() {
        return payer;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public User getOwner() {
        return owner;
    }

    public Collection<User> getAttendees() {
        return attendees;
    }
}

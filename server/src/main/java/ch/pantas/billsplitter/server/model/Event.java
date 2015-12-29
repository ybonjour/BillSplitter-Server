package ch.pantas.billsplitter.server.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

public class Event implements Serializable {
    private static final long serialVersionUID = -8979419621719034542L;

    private UUID id;
    private String description;
    private String currency;
    private User owner;
    private Collection<User> participants;
    private Collection<Expense> expenses;

    protected Event(){ }

    public Event(UUID id, String description, String currency, User owner, Collection<User> participants,
                 Collection<Expense> expenses) {
        this.id = id;
        this.description = description;
        this.currency = currency;
        this.owner = owner;
        this.participants = participants;
        this.expenses = expenses;
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

    public Collection<User> getParticipants() {
        return participants;
    }

    public Collection<Expense> getExpenses() {
        return expenses;
    }
}

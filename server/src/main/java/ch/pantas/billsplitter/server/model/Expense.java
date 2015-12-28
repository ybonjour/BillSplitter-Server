package ch.pantas.billsplitter.server.model;

import java.io.Serializable;
import java.util.UUID;

public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;


    private UUID id;
    private Event event;
    private Participant payer;
    private String description;
    private int amount;
    private User owner;

    protected Expense() { }

    public Expense(UUID id, Event event, Participant payer, String description, int amount, User owner) {
        this.id = id;
        this.event = event;
        this.payer = payer;
        this.description = description;
        this.amount = amount;
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public Participant getPayer() {
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
}

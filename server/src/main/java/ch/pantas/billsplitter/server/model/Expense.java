package ch.pantas.billsplitter.server.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Expense {

    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @NaturalId
    private Event event;

    @ManyToOne(optional = false)
    @NaturalId
    private Participant payer;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private int amount;

    @ManyToOne(optional = false)
    @NaturalId
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

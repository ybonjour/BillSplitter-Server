package ch.pantas.billsplitter.server.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Event {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String currency;

    @ManyToOne(optional = false)
    @NaturalId
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

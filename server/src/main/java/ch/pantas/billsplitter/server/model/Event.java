package ch.pantas.billsplitter.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String currency;

    @ManyToOne(optional = false)
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

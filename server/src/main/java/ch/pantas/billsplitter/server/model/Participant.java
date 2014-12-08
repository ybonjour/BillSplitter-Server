package ch.pantas.billsplitter.server.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Participant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @NaturalId
    private Event event;

    @ManyToOne(optional = false)
    @NaturalId
    private User user;

    protected Participant() { }

    public Participant(UUID id, Event event, User user) {
        this.id = id;
        this.event = event;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public User getUser() {
        return user;
    }
}

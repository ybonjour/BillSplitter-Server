package ch.pantas.billsplitter.server.model;

import java.io.Serializable;
import java.util.UUID;

public class Participant implements Serializable {
    private static final long serialVersionUID = 2545769918825777204L;

    private UUID id;
    private Event event;
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

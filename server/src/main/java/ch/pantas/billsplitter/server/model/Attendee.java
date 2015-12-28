package ch.pantas.billsplitter.server.model;

import java.io.Serializable;
import java.util.UUID;

public class Attendee implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private Expense expense;
    private Participant participant;

    protected Attendee() { }

    public Attendee(UUID id, Expense expense, Participant participant) {
        this.id = id;
        this.expense = expense;
        this.participant = participant;
    }

    public UUID getId() {
        return id;
    }

    public Expense getExpense() {
        return expense;
    }

    public Participant getParticipant() {
        return participant;
    }
}

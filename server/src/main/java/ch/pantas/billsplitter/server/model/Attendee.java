package ch.pantas.billsplitter.server.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Attendee {

    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @NaturalId
    private Expense expense;

    @ManyToOne(optional = false)
    @NaturalId
    private Participant participant;

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

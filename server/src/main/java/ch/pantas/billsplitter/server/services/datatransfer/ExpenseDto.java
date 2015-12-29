package ch.pantas.billsplitter.server.services.datatransfer;

import ch.pantas.billsplitter.server.model.Expense;
import ch.pantas.billsplitter.server.model.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExpenseDto implements Serializable {
    private static final long serialVersionUID = -2929896912733451664L;

    private UUID id;
    private UUID payer;
    private String description;
    private int amount;
    private UUID owner;
    private Collection<UUID> attendees;

    public ExpenseDto() {
    }

    public ExpenseDto(Expense expense) {
        this.id = expense.getId();
        this.payer = expense.getPayer().getId();
        this.description = expense.getDescription();
        this.amount = expense.getAmount();
        this.owner = expense.getOwner().getId();
        this.attendees = expense.getAttendees().stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }

    public UUID getId() {
        return id;
    }

    public UUID getPayer() {
        return payer;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public UUID getOwner() {
        return owner;
    }

    public Collection<UUID> getAttendees() {
        return attendees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpenseDto that = (ExpenseDto) o;

        if (amount != that.amount) return false;
        if (!id.equals(that.id)) return false;
        if (!payer.equals(that.payer)) return false;
        if (!description.equals(that.description)) return false;
        if (!owner.equals(that.owner)) return false;
        return attendees.equals(that.attendees);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + payer.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + amount;
        result = 31 * result + owner.hashCode();
        result = 31 * result + attendees.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ExpenseDto{" +
                "id=" + id +
                ", payer=" + payer +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", owner=" + owner +
                ", attendees=" + attendees +
                '}';
    }
}

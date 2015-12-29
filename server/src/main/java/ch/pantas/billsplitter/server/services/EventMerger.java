package ch.pantas.billsplitter.server.services;

import ch.pantas.billsplitter.server.model.Event;
import ch.pantas.billsplitter.server.model.Expense;
import ch.pantas.billsplitter.server.model.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class EventMerger {
    public static Event merge(Event oldEvent, Event updatedEvent, UUID user) {
        assert (oldEvent.getId().equals(updatedEvent.getId()));

        String description = oldEvent.getDescription();
        String currency = oldEvent.getCurrency();
        Collection<User> participants = oldEvent.getParticipants();
        if (oldEvent.getOwner().getId().equals(user)) {
            description = updatedEvent.getDescription();
            currency = updatedEvent.getCurrency();
            participants = updatedEvent.getParticipants();
        }

        List<Expense> oldExpensesNotByUser = oldEvent.getExpenses().stream()
                .filter(e -> !e.getOwner().getId().equals(user))
                .collect(Collectors.toList());
        List<Expense> updatedExpensesByUser = updatedEvent.getExpenses().stream()
                .filter(e -> e.getOwner().getId().equals(user))
                .collect(Collectors.toList());

        List<Expense> expenses = oldExpensesNotByUser;
        expenses.addAll(updatedExpensesByUser);

        return new Event(oldEvent.getId(), description, currency, oldEvent.getOwner(), participants, expenses);
    }
}

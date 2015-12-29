package ch.pantas.billsplitter.server.services;

import ch.pantas.billsplitter.server.model.Event;
import ch.pantas.billsplitter.server.model.Expense;
import ch.pantas.billsplitter.server.model.User;
import ch.pantas.billsplitter.server.services.datatransfer.EventDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EventBuilder {
    public static EventBuilder event() {
        return new EventBuilder();
    }

    private UUID id;
    private String description;
    private String currency;
    private UserBuilder owner;
    private List<UserBuilder> participantBuilders = new ArrayList<>();
    private List<ExpenseBuilder> expenseBuilders = new ArrayList<>();

    private Optional<Event> event = Optional.empty();

    public EventBuilder() {
        id = UUID.randomUUID();
        description = "desc";
        currency = "CHF";
        owner = UserBuilder.user();
    }

    public EventBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public EventBuilder description(String d) {
        this.description = d;
        return this;
    }

    public EventBuilder currency(String c) {
        this.currency = c;
        return this;
    }

    public EventBuilder withOwner(UserBuilder owner) {
        this.owner = owner;
        return this;
    }

    public EventBuilder withParticipant(UserBuilder participant) {
        this.participantBuilders.add(participant);
        return this;
    }

    public EventBuilder withExpense(ExpenseBuilder expense) {
        this.expenseBuilders.add(expense);
        return this;
    }

    public Event build() {
        if (!event.isPresent()) {
            List<User> participants = participantBuilders.stream()
                    .map(UserBuilder::build)
                    .collect(Collectors.toList());

            List<Expense> expenses = expenseBuilders.stream()
                    .map(ExpenseBuilder::build)
                    .collect(Collectors.toList());

            event = Optional.of(new Event(id, description, currency, owner.build(), participants, expenses));
        }

        return event.get();
    }

    public EventDto buildDto() {
        return new EventDto(build());
    }
}

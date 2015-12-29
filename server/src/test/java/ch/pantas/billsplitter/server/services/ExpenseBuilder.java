package ch.pantas.billsplitter.server.services;

import ch.pantas.billsplitter.server.model.Expense;
import ch.pantas.billsplitter.server.model.User;
import ch.pantas.billsplitter.server.services.datatransfer.ExpenseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExpenseBuilder {
    public static ExpenseBuilder expense() {
        return new ExpenseBuilder();
    }

    private UUID id;
    private EventBuilder event;
    private UserBuilder payer;
    private String description;
    private int amount;
    private UserBuilder owner;
    private List<UserBuilder> attendeeBuilders = new ArrayList<>();

    private Optional<Expense> expense = Optional.empty();

    public ExpenseBuilder() {
        id = UUID.randomUUID();
        event = EventBuilder.event();
        payer = UserBuilder.user();
        description = "desc";
        amount = 123;
        owner = UserBuilder.user();
    }

    public ExpenseBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public ExpenseBuilder withEvent(EventBuilder event) {
        this.event = event;
        return this;
    }

    public ExpenseBuilder withPayer(UserBuilder payer) {
        this.payer = payer;
        return this;
    }

    public ExpenseBuilder description(String desc) {
        this.description = desc;
        return this;
    }

    public ExpenseBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ExpenseBuilder withOwner(UserBuilder owner) {
        this.owner = owner;
        return this;
    }

    public ExpenseBuilder withAttendee(UserBuilder attendee) {
        this.attendeeBuilders.add(attendee);
        return this;
    }

    public Expense build() {
        if (!expense.isPresent()) {
            List<User> attendees = attendeeBuilders.stream()
                    .map(UserBuilder::build)
                    .collect(Collectors.toList());
            expense = Optional.of(new Expense(id, payer.build(), description, amount, owner.build(), attendees));
        }

        return expense.get();
    }

    public ExpenseDto buildDto() {
        return new ExpenseDto(build());
    }
}

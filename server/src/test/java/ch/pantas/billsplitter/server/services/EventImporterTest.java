package ch.pantas.billsplitter.server.services;

import ch.pantas.billsplitter.server.services.datatransfer.EventDto;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static ch.pantas.billsplitter.server.services.EventBuilder.event;
import static ch.pantas.billsplitter.server.services.UserBuilder.user;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EventImporterTest {

    private EventImporter eventImporter;
    private UserBuilder userA;
    private UserBuilder userB;

    @Before
    public void setUp() throws Exception {
        eventImporter = new EventImporter();

        userA = user()
                .name("User A");

        userB = user()
                .name("User B");
    }

    @Test
    public void eventParticipantsDescriptionAndCurrencyAreCorrectlyAddedAndUpdated() throws Exception {
        UUID eventId = UUID.randomUUID();

        EventDto event = event()
                .id(eventId)
                .withOwner(userA)
                .withParticipant(userA)
                .withParticipant(userB)
                .buildDto();

        eventImporter.updateEvent(event, userA.build().getId());
        assertThat(eventImporter.getEvent(eventId).getParticipants(),
                Matchers.containsInAnyOrder(userA.buildDto(), userB.buildDto()));

        String newCurrency = "USD";
        String newDescription = "abcdefg";
        EventDto updatedEvent = event()
                .id(eventId)
                .description(newDescription)
                .currency(newCurrency)
                .withOwner(userA)
                .withParticipant(userA)
                .buildDto();

        eventImporter.updateEvent(updatedEvent, userA.build().getId());
        EventDto result = eventImporter.getEvent(eventId);
        assertThat(result.getParticipants(),
                Matchers.containsInAnyOrder(userA.buildDto()));
        assertThat(result.getCurrency(), is(newCurrency));
        assertThat(result.getDescription(), is(newDescription));
    }

    @Test
    public void userNameIsCorrectlyUpdated() throws Exception {
        UUID eventId = UUID.randomUUID();

        EventDto event = event()
                .id(eventId)
                .withOwner(userA)
                .withParticipant(userA)
                .buildDto();

        eventImporter.updateEvent(event, userA.build().getId());

        String newName = "New name";
        UserBuilder updatedUserA = user()
                .id(userA.build().getId())
                .name(newName);

        EventDto updatedEvent = event()
                .id(eventId)
                .withOwner(updatedUserA)
                .withParticipant(updatedUserA)
                .buildDto();

        eventImporter.updateEvent(updatedEvent, updatedUserA.build().getId());
        EventDto result = eventImporter.getEvent(eventId);
        assertThat(result.getOwner().getName(), is(newName));
        assertThat(result.getParticipants().iterator().next().getName(), is(newName));
    }

    @Test
    public void eventExpensesAreCorrectlyAddedAndUpdated() throws Exception {
        UUID eventId = UUID.randomUUID();

        ExpenseBuilder expenseA = ExpenseBuilder.expense()
                .withOwner(userA)
                .withPayer(userB)
                .withAttendee(userA)
                .withAttendee(userB);
        ExpenseBuilder expenseB = ExpenseBuilder.expense()
                .withOwner(userA)
                .withPayer(userB)
                .withAttendee(userA)
                .withAttendee(userB);

        EventDto event = event()
                .id(eventId)
                .withOwner(userA)
                .withParticipant(userA)
                .withParticipant(userB)
                .withExpense(expenseA)
                .withExpense(expenseB)
                .buildDto();

        eventImporter.updateEvent(event, userA.build().getId());
        assertThat(eventImporter.getEvent(eventId).getExpenses(),
                Matchers.containsInAnyOrder(expenseA.buildDto(), expenseB.buildDto()));

        EventDto updatedEvent = event()
                .id(eventId)
                .withOwner(userA)
                .withParticipant(userA)
                .withParticipant(userB)
                .withExpense(expenseA)
                .buildDto();

        eventImporter.updateEvent(updatedEvent, userA.build().getId());
        assertThat(eventImporter.getEvent(eventId).getExpenses(),
                Matchers.containsInAnyOrder(expenseA.buildDto()));
    }

    @Test
    public void expenseAttendeesAreCorrectlyAddedAndUpdated() throws Exception {
        UUID eventId = UUID.randomUUID();
        UUID expenseId = UUID.randomUUID();

        ExpenseBuilder expenseA = ExpenseBuilder.expense()
                .id(expenseId)
                .withOwner(userA)
                .withPayer(userB)
                .withAttendee(userB);
        ExpenseBuilder expenseB = ExpenseBuilder.expense()
                .id(expenseId)
                .withOwner(userA)
                .withPayer(userA)
                .withAttendee(userA);

        EventDto event = event()
                .id(eventId)
                .withOwner(userA)
                .withParticipant(userA)
                .withParticipant(userB)
                .withExpense(expenseA)
                .buildDto();

        eventImporter.updateEvent(event, userA.build().getId());
        assertThat(eventImporter.getEvent(eventId).getExpenses(),
                Matchers.containsInAnyOrder(expenseA.buildDto()));

        EventDto updatedEvent = event()
                .id(eventId)
                .withOwner(userA)
                .withParticipant(userA)
                .withParticipant(userB)
                .withExpense(expenseB)
                .buildDto();

        eventImporter.updateEvent(updatedEvent, userA.build().getId());
        assertThat(eventImporter.getEvent(eventId).getExpenses(),
                Matchers.containsInAnyOrder(expenseB.buildDto()));
    }

    @Test
    public void onlyOwnerCanChangeEvent() throws Exception {
        UUID eventId = UUID.randomUUID();

        EventDto event = event()
                .id(eventId)
                .withOwner(userA)
                .withParticipant(userA)
                .withParticipant(userB)
                .buildDto();

        eventImporter.updateEvent(event, userB.build().getId());
        assertThat(eventImporter.getEvent(eventId).getParticipants(),
                Matchers.containsInAnyOrder(userA.buildDto(), userB.buildDto()));

        EventDto updatedEvent = event()
                .id(eventId)
                .withOwner(userA)
                .withParticipant(userA)
                .buildDto();

        eventImporter.updateEvent(updatedEvent, userB.build().getId());
        assertThat(eventImporter.getEvent(eventId).getParticipants(),
                Matchers.containsInAnyOrder(userA.buildDto(), userB.buildDto()));
    }

    @Test
    public void onlyOwnerCanChangeExpense() throws Exception {
        UUID eventId = UUID.randomUUID();

        ExpenseBuilder expenseA = ExpenseBuilder.expense()
                .withOwner(userA)
                .withPayer(userB)
                .withAttendee(userA)
                .withAttendee(userB);
        ExpenseBuilder expenseB = ExpenseBuilder.expense()
                .withOwner(userB)
                .withPayer(userB)
                .withAttendee(userA)
                .withAttendee(userB);

        EventDto event = event()
                .id(eventId)
                .withOwner(userA)
                .withParticipant(userA)
                .withParticipant(userB)
                .withExpense(expenseA)
                .withExpense(expenseB)
                .buildDto();

        eventImporter.updateEvent(event, userA.build().getId());
        assertThat(eventImporter.getEvent(eventId).getExpenses(),
                Matchers.containsInAnyOrder(expenseA.buildDto(), expenseB.buildDto()));

        EventDto updatedEvent = event()
                .id(eventId)
                .withOwner(userA)
                .withParticipant(userA)
                .withParticipant(userB)
                .withExpense(expenseA)
                .buildDto();

        eventImporter.updateEvent(updatedEvent, userA.build().getId());
        assertThat(eventImporter.getEvent(eventId).getExpenses(),
                Matchers.containsInAnyOrder(expenseA.buildDto(), expenseB.buildDto()));
    }
}
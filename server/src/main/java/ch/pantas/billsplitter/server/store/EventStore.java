package ch.pantas.billsplitter.server.store;

import ch.pantas.billsplitter.server.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventStore extends CrudRepository<Event, UUID>{
}

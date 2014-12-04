package ch.pantas.billsplitter.server.store;

import ch.pantas.billsplitter.server.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserStore extends CrudRepository<User, UUID>{
}

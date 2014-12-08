package ch.pantas.billsplitter.server

import ch.pantas.billsplitter.server.model.User
import ch.pantas.billsplitter.server.store.UserStore
import org.springframework.beans.factory.annotation.Autowired

import static java.util.UUID.randomUUID


class UserStoreTest extends SpringTestBase {

    @Autowired
    private UserStore userStore;

    def "test insert User does not throw an exception"() {
        given:
        def user = new User(randomUUID(), "Joe")

        when:
        userStore.save(user)

        then:
        notThrown(Throwable.class)
    }
}

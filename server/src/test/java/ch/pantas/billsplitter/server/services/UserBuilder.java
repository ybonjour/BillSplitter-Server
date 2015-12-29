package ch.pantas.billsplitter.server.services;

import ch.pantas.billsplitter.server.model.User;
import ch.pantas.billsplitter.server.services.datatransfer.UserDto;

import java.util.Optional;
import java.util.UUID;

public class UserBuilder {
    public static UserBuilder user() {
        return new UserBuilder();
    }

    private UUID id;
    private String name;

    private Optional<User> user = Optional.empty();

    public UserBuilder() {
        id = UUID.randomUUID();
        name = "Test User";
    }

    public UserBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public UserBuilder name(String n) {
        this.name = n;
        return this;
    }

    public User build() {
        if (!user.isPresent()) {
            user = Optional.of(new User(id, name));
        }

        return user.get();
    }

    public UserDto buildDto() {
        return new UserDto(build());
    }
}

package ch.pantas.billsplitter.server.services.datatransfer;

import java.util.UUID;

import ch.pantas.billsplitter.server.model.User;

public class UserDto {
    private UUID id;
    private String name;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

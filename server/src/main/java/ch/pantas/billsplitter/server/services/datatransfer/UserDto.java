package ch.pantas.billsplitter.server.services.datatransfer;

import ch.pantas.billsplitter.server.model.User;

import java.io.Serializable;
import java.util.UUID;

public class UserDto implements Serializable {
    private static final long serialVersionUID = -6214951008797801424L;

    private UUID id;
    private String name;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

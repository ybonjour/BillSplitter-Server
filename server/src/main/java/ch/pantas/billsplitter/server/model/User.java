package ch.pantas.billsplitter.server.model;

import ch.pantas.billsplitter.server.services.datatransfer.UserDto;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private static final long serialVersionUID = 8640651636397922257L;

    private UUID id;
    private String name;

    protected User() {
    }

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(UserDto user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

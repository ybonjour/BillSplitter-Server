package ch.pantas.billsplitter.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    protected User() { }

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

package ch.pantas.billsplitter.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class User implements Serializable {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String userName;

    protected User() { }

    public User(UUID id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}

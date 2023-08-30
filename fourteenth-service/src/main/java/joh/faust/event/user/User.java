package joh.faust.event.user;

import lombok.Getter;

import java.util.UUID;

public class User {

    @Getter
    private final UUID userId;

    @Getter
    private final String userName;

    public User(UUID userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}

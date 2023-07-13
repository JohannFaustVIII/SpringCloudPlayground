package joh.faust.model;

import java.util.List;
import java.util.stream.Collectors;

public record User(long id, String name, List<Post> posts) {

    public static User fromEntity(joh.faust.entity.User entityUser) {
        return fromEntity(
                entityUser,
                entityUser
                        .getPosts()
                        .stream()
                        .map(post -> Post.fromEntity(post, null))
                        .collect(Collectors.toList())
        );
    }

    public static User fromEntity(joh.faust.entity.User entityUser, List<Post> posts) {
        return new User(
                entityUser.getId(),
                entityUser.getName(),
                posts
        );
    }

}

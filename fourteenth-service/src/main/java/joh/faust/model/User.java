package joh.faust.model;

import java.util.List;
import java.util.stream.Collectors;

public record User(long id, String name, List<Post> posts) {

    public static User fromEntity(joh.faust.entity.User entityUser) {
        return new User(
                entityUser.getId(),
                entityUser.getName(),
                entityUser.getPosts().stream().map(Post::fromEntity).collect(Collectors.toList())
        );
    }

}

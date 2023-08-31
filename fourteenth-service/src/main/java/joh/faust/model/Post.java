package joh.faust.model;

import java.util.UUID;

public record Post(UUID id, String name, String content, UUID user) {

    public static Post fromEntity(joh.faust.entity.Post entityPost) {
        return fromEntity(
                entityPost,
                User.fromEntity(entityPost.getUser(), null)
        );
    }

    public static Post fromEntity(joh.faust.entity.Post entityPost, User user) {
        return new Post(
                entityPost.getId(),
                entityPost.getName(),
                entityPost.getContent(),
                user.id());
    }
}

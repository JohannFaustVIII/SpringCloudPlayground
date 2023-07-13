package joh.faust.model;

public record Post(long id, String name, String content, User user) {

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
                user);
    }
}

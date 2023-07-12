package joh.faust.model;

public record Post(long id, String name, String content) {

    public static Post fromEntity(joh.faust.entity.Post entityPost) {
        return new Post(
                entityPost.getId(),
                entityPost.getName(),
                entityPost.getContent()
        );
    }
}

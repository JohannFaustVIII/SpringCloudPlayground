package joh.faust.event.post;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Post {
    @Getter
    private UUID postId;
    @Setter
    private String postName;
    @Setter
    private String postContent;
    private UUID creatorId;

    public Post(UUID postId, String postName, String postContent, UUID creatorId) {
        this.postId = postId;
        this.postName = postName;
        this.postContent = postContent;
        this.creatorId = creatorId;
    }

}
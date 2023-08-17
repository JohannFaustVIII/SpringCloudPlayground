package joh.faust.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PostUsers")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    private UUID id;

    @Setter
    private String name;

    @OneToMany(mappedBy = "user")
    @Fetch(FetchMode.JOIN)
    private List<Post> posts;

    public User(String name) {
        this(name, new ArrayList<>());
    }

    public User(String name, List<Post> posts) {
        this.name = name;
        this.posts = posts;
    }
}

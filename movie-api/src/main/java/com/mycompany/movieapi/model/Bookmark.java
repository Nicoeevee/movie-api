package com.mycompany.movieapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ZonedDateTime createdAt;
    @NotNull
    @Size(min = 5, max = 50, message = "length of Name must be between 5 and 50")
    private String name;
    @NotNull
    @Size(min = 5, max = 1000)
    @Column(name = "description", columnDefinition = "TEXT", length = 1024)
    private String description;
    @ManyToMany
    private Set<Tag> tags;

    @PrePersist
    public void onPrePersist() {
        createdAt = ZonedDateTime.now();
    }
}

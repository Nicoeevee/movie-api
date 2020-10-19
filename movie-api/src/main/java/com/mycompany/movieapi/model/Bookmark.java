package com.mycompany.movieapi.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "bookmarks")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50, message = "length of Name must be between 5 and 50")
    private String name;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updateTime;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createTime;

    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "description", columnDefinition = "TEXT", length = 1024)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private User user;

    public Bookmark(@NotNull @Size(min = 1, max = 50, message = "length of Name must be between 5 and 50") String name, @NotNull @Size(min = 1, max = 1000) String description) {
        this.name = name;
        this.description = description;
    }
}

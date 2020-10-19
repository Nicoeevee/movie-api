package com.mycompany.movieapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    private String imdb;
    private String title;
    private String poster;
    private String url;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updateTime;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createTime;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Bookmark> bookmarks;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Tag> tags;

    public Movie(String imdb, String title, String poster, String url) {
        this.imdb = imdb;
        this.title = title;
        this.poster = poster;
        this.url = url;
    }
}

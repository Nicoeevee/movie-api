package com.mycompany.movieapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.ZonedDateTime;

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

    private ZonedDateTime createdAt;

    public Movie(String imdb, String title, String poster, String url) {
        this.imdb = imdb;
        this.title = title;
        this.poster = poster;
        this.url = url;
    }

    @PrePersist
    public void onPrePersist() {
        createdAt = ZonedDateTime.now();
    }

}

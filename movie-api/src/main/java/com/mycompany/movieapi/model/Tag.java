package com.mycompany.movieapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 1, max = 20, message = "length of tag must be between 2 and 20")
    private String name;
    private boolean inactive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imdb_id")
    private Movie movie;
}

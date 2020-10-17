package com.mycompany.movieapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 2, max = 20, message = "length of tag must be between 2 and 20")
    private String name;
    private boolean inactive;
}

package com.mycompany.movieapi.rest.dto;

import com.mycompany.movieapi.model.Tag;
import lombok.Data;

import java.util.Set;

@Data
public class BookmarkDto {
    private Long id;
    private String createdAt;
    private String name;
    private String description;
    private Set<Tag> tags;
}

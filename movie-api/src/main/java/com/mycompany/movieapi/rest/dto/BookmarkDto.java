package com.mycompany.movieapi.rest.dto;

import com.mycompany.movieapi.model.Tag;
import com.mycompany.movieapi.model.User;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class BookmarkDto {
    private Long id;
    private String name;
    private String description;
    private Date updateTime;
    private Date createTime;
    private Set<Tag> tags;
    private User user;
}

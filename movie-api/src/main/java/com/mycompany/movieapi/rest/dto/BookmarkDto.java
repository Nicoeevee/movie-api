package com.mycompany.movieapi.rest.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookmarkDto {
    private Long id;
    private String name;
    private String description;
    private Date updateTime;
    private Date createTime;
    private MovieDto movie;
    private UserDto user;
}

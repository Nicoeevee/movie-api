package com.mycompany.movieapi.rest.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MovieDto {

    private String imdb;
    private String title;
    private String poster;
    private String url;
    private Date updateTime;
    private Date createTime;
}

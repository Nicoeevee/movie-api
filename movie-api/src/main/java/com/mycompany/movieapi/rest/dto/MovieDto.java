package com.mycompany.movieapi.rest.dto;

import com.mycompany.movieapi.model.Bookmark;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class MovieDto {

  private String imdb;
  private String title;
  private String poster;
  private String url;
  private Date updateTime;
  private Date createTime;
  private Set<Bookmark> bookmarks;
}

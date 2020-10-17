package com.mycompany.movieapi.rest.dto;

import com.mycompany.movieapi.model.Bookmark;
import lombok.Data;

import java.util.Set;

@Data
public class MovieDto {

  private String imdb;
  private String title;
  private String poster;
  private String url;
  private String createdAt;
  private Set<Bookmark> bookmarks;
}

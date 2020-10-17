package com.mycompany.movieapi.mapper;

import com.mycompany.movieapi.model.Bookmark;
import com.mycompany.movieapi.model.Movie;
import com.mycompany.movieapi.rest.dto.BookmarkDto;
import com.mycompany.movieapi.rest.dto.CreateBookmarkRequest;
import com.mycompany.movieapi.rest.dto.CreateMovieRequest;
import com.mycompany.movieapi.rest.dto.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface BookmarkMapper {

  Bookmark toBookmark(CreateBookmarkRequest createBookmarkRequest);

  @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  BookmarkDto toBookmarkDto(Bookmark bookmark);

}
package com.mycompany.movieapi.mapper;

import com.mycompany.movieapi.model.Bookmark;
import com.mycompany.movieapi.rest.dto.BookmarkDto;
import com.mycompany.movieapi.rest.dto.CreateBookmarkRequest;
import com.mycompany.movieapi.service.BookmarkService;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = BookmarkService.class
)
public interface BookmarkMapper {

    Bookmark toBookmark(CreateBookmarkRequest createBookmarkRequest);

    BookmarkDto toBookmarkDto(Bookmark bookmark);

}
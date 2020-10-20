package com.mycompany.movieapi.rest;

import com.mycompany.movieapi.mapper.BookmarkMapper;
import com.mycompany.movieapi.model.Bookmark;
import com.mycompany.movieapi.model.Movie;
import com.mycompany.movieapi.model.User;
import com.mycompany.movieapi.rest.dto.BookmarkDto;
import com.mycompany.movieapi.rest.dto.CreateBookmarkRequest;
import com.mycompany.movieapi.service.BookmarkService;
import com.mycompany.movieapi.service.MovieService;
import com.mycompany.movieapi.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.mycompany.movieapi.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final BookmarkMapper bookmarkMapper;
    private final MovieService movieService;
    private final TagService tagService;

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping
    public List<BookmarkDto> getBookmarks(@RequestParam(value = "text", required = false) String imdbId) {
        List<Bookmark> bookmarks = (imdbId == null) ? bookmarkService.getBookmarks() : bookmarkService.getBookmarksByMovieId(imdbId);
        return bookmarks.stream()
                .map(bookmarkMapper::toBookmarkDto)
                .collect(Collectors.toList());
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookmarkDto createBookmark(@Valid @RequestBody CreateBookmarkRequest createBookmarkRequest) {
        Bookmark bookmark = bookmarkMapper.toBookmark(createBookmarkRequest);
        Movie movie = movieService.validateAndGetMovie(createBookmarkRequest.getImdb());
        bookmark.setMovie(movie);
        bookmark.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return bookmarkMapper.toBookmarkDto(bookmarkService.saveBookmark(bookmark));
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping("/{id}")
    public BookmarkDto deleteBookmark(@PathVariable Long id) {
        Bookmark bookmark = bookmarkService.getBookmarkById(id);
        bookmarkService.deleteBookmarkById(id);
        return bookmarkMapper.toBookmarkDto(bookmark);
    }

}

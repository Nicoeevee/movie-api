package com.mycompany.movieapi.rest;

import com.mycompany.movieapi.mapper.BookmarkMapper;
import com.mycompany.movieapi.model.Bookmark;
import com.mycompany.movieapi.rest.dto.BookmarkDto;
import com.mycompany.movieapi.rest.dto.CreateBookmarkRequest;
import com.mycompany.movieapi.service.BookmarkService;
import com.mycompany.movieapi.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.mycompany.movieapi.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final BookmarkMapper bookmarkMapper;
    private final TagService tagService;

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping
    public List<Bookmark> getBookmarks() {
        return bookmarkService.getBookmarks();
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookmarkDto createBookmark(@Valid @RequestBody CreateBookmarkRequest createBookmarkRequest) {
        Bookmark bookmark = bookmarkMapper.toBookmark(createBookmarkRequest);
        return bookmarkMapper.toBookmarkDto(bookmarkService.saveBookmark(bookmark));
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping("/{id}")
    public Bookmark deleteBookmark(@PathVariable Long id) {
        Bookmark bookmark = bookmarkService.getBookmarkById(id);
        bookmarkService.deleteBookmarkById(id);
        return bookmark;
    }

}

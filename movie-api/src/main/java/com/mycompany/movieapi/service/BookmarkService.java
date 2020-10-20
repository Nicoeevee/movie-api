package com.mycompany.movieapi.service;

import com.mycompany.movieapi.model.Bookmark;

import java.util.List;

public interface BookmarkService {
    Bookmark getBookmarkById(Long id);

    List<Bookmark> getBookmarksByMovieId(String movieId);

    List<Bookmark> getBookmarks();

    Bookmark saveBookmark(Bookmark bookmark);

    void deleteBookmarkById(Long id);
}

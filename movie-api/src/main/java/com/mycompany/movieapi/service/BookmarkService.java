package com.mycompany.movieapi.service;

import com.mycompany.movieapi.model.Bookmark;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface BookmarkService {
    Bookmark getBookmarkById(Long id);

    List<Bookmark> getBookmarks();

    Bookmark saveBookmark(Bookmark bookmark);

    void deleteBookmarkById(Long id);
}

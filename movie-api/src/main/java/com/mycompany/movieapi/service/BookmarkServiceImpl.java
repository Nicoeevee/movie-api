package com.mycompany.movieapi.service;

import com.mycompany.movieapi.exception.BookmarkException;
import com.mycompany.movieapi.model.Bookmark;
import com.mycompany.movieapi.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Override
    @Cacheable(value = "bookmarks", key = "#id")
    public Bookmark getBookmarkById(Long id) {
        return bookmarkRepository.findById(id)
                .orElseThrow(() -> new BookmarkException(String.format("No bookmark record exist for %d", id)));
    }

    @Override
    public List<Bookmark> getBookmarks() {
        return bookmarkRepository.findAll();
    }

    @Override
    @CacheEvict(value = "bookmarks", key = "#bookmark.id")
    public Bookmark saveBookmark(Bookmark bookmark) {
       return bookmarkRepository.save(bookmark);
    }

    @Override
    @CacheEvict(value = "bookmarks", key = "#id")
    public void deleteBookmarkById(Long id) {
        try {
            bookmarkRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new BookmarkException("No bookmark record exist to delete");
        }
    }
}

package com.mycompany.movieapi.repository;


import com.mycompany.movieapi.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findBookmarksByMovie_Imdb(String imdbId);

}

package com.mycompany.movieapi.repository;


import com.mycompany.movieapi.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}

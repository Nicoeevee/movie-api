package com.mycompany.movieapi.repository;

import com.mycompany.movieapi.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}

package com.mycompany.movieapi.service;

import com.mycompany.movieapi.model.Tag;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TagService {
    Tag getTagById(Long id);

    List<Tag> getTags(Integer page, Integer pageSize, String sortBy);

    void saveTag(Tag tag);

    void deleteTagById(Long id);

    Page<Tag> getTagsPage(Integer page, Integer pageSize, String sortBy);

    void deleteAllTags();

    List<Tag> getTags();
}

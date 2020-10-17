package com.mycompany.movieapi.service;

import com.mycompany.movieapi.exception.TagException;
import com.mycompany.movieapi.model.Tag;
import com.mycompany.movieapi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag getTagById(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            return tag.get();
        } else {
            throw new TagException("No tag record exist for given id");
        }
    }

    @Override
    public List<Tag> getTags(Integer page, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        Page<Tag> tagPage = tagRepository.findAll(pageable);
        return tagPage.getContent();
    }

    @Override
    public Page<Tag> getTagsPage(Integer page, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return tagRepository.findAll(pageable);
    }

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public void deleteAllTags() {
        tagRepository.deleteAll();
    }

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

}

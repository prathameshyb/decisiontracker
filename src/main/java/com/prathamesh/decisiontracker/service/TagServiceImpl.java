package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.dto.CreateTagDTO;
import com.prathamesh.decisiontracker.dto.TagDTO;
import com.prathamesh.decisiontracker.dto.TagMapper;
import com.prathamesh.decisiontracker.dto.UpdateTagDTO;
import com.prathamesh.decisiontracker.entities.Tag;
import com.prathamesh.decisiontracker.exception.DuplicateEntryException;
import com.prathamesh.decisiontracker.exception.ResourceNotFoundException;
import com.prathamesh.decisiontracker.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<TagDTO> getTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(tagMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void addTag(CreateTagDTO createTagDTO) throws Exception {
        Tag tag = tagMapper.toEntity(createTagDTO);
        if (tagRepository.existsByTagName(tag.getTagName())) {
            throw new DuplicateEntryException("Tag name must be unique");
        }
        tagRepository.save(tag);
    }

    @Override
    public void updateTag(UpdateTagDTO updateTagDTO) throws Exception {
        Tag updateTag = tagRepository.findById(updateTagDTO.getTagId())
                .orElseThrow(() -> new ResourceNotFoundException("Tag", updateTagDTO.getTagId()));
        Tag tag = tagMapper.toEntity(updateTagDTO);
        tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Integer tagId) throws Exception {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", tagId));
        tagRepository.delete(tag);
    }
}

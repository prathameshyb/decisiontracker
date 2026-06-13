package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.constants.TagConstants;
import com.prathamesh.decisiontracker.dto.*;
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

    private final DecisionMapper decisionMapper;

    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper, DecisionMapper decisionMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
        this.decisionMapper = decisionMapper;
    }

    @Override
    public List<TagDTO> getTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(tagMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TagDTO getTagById(Integer tagId) throws Exception {
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", tagId));
        return tagMapper.toDTO(tag);
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

    @Override
    public List<DecisionDTO> getTagDecisions(Integer tagId) throws Exception {
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", tagId));
        return tag.getDecisions().stream().map(decisionMapper::toDTO).toList();
    }

    @Override
    public List<TopTagDTO> getTopTags() {
        return tagRepository.getTopTags(TagConstants.MINIMUM_USAGE_COUNT)
                .stream()
                .map(tagMapper::toTopTagDTO)
                .toList();
    }

    @Override
    public List<TagScoreDTO> getAverageScorePerTag() {
//    return tagRepository.getAverageScorePerTag()
//            .stream()
//            .map(tagMapper::toTagScoreDTO)
//            .toList();
    return tagRepository.getAvgScorePerTag();
    }

}

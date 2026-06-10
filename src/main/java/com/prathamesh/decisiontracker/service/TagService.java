package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.dto.CreateTagDTO;
import com.prathamesh.decisiontracker.dto.DecisionDTO;
import com.prathamesh.decisiontracker.dto.TagDTO;
import com.prathamesh.decisiontracker.dto.UpdateTagDTO;

import java.util.List;

public interface TagService {

    List<TagDTO> getTags();

    TagDTO getTagById(Integer tagId) throws Exception;

    void addTag(CreateTagDTO createTagDTO) throws Exception;

    void updateTag(UpdateTagDTO updateTagDTO) throws Exception;

    void deleteTag(Integer tagId) throws Exception;

    List<DecisionDTO> getTagDecisions(Integer tagId) throws Exception;
}

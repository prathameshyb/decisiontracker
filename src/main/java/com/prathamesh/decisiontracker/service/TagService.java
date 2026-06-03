package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.dto.CreateTagDTO;
import com.prathamesh.decisiontracker.dto.TagDTO;
import com.prathamesh.decisiontracker.dto.UpdateTagDTO;

import java.util.List;

public interface TagService {

    List<TagDTO> getTags();

    void addTag(CreateTagDTO createTagDTO) throws Exception;

    void updateTag(UpdateTagDTO updateTagDTO) throws Exception;

    void deleteTag(Integer tagId) throws Exception;
}

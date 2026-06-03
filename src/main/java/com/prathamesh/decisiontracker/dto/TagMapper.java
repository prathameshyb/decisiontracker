package com.prathamesh.decisiontracker.dto;

import com.prathamesh.decisiontracker.entities.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public TagDTO toDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setTagId(tag.getTagId());
        tagDTO.setTagName(tag.getTagName());
        return tagDTO;
    }

    public Tag toEntity(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setTagName(tagDTO.getTagName());
        return tag;
    }

    public Tag toEntity(CreateTagDTO createTagDTO) {
        Tag tag = new Tag();
        tag.setTagName(createTagDTO.getTagName());
        return tag;
    }

    public Tag toEntity(UpdateTagDTO updateTagDTO) {
        Tag tag = new Tag();
        tag.setTagId(updateTagDTO.getTagId());
        tag.setTagName(updateTagDTO.getTagName());
        return tag;
    }
}


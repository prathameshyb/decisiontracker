package com.prathamesh.decisiontracker.dto;

import com.prathamesh.decisiontracker.entities.Tag;
import com.prathamesh.decisiontracker.service.TagService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class TagMapper {

    public TagDTO toDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setTagId(tag.getTagId());
        tagDTO.setTagName(tag.getTagName());
        return tagDTO;
    }

    public TopTagDTO toTopTagDTO(Object[] result){
        TopTagDTO topTagDTO = new TopTagDTO();
        topTagDTO.setTagId((int) result[0]);
        topTagDTO.setTagName((String) result[1]);
        topTagDTO.setUsageCount(((Number) result[2]).intValue());
        return topTagDTO;
    }

//    public TagScoreDTO toTagScoreDTO(Object[] result){
//        TagScoreDTO tagScoreDTO = new TagScoreDTO();
//        tagScoreDTO.setTagName((String) result[0]);
//        tagScoreDTO.setTagId((int) result[1]);
//        tagScoreDTO.setAverageScore((Double) result[2]);
//        return tagScoreDTO;
//    }

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


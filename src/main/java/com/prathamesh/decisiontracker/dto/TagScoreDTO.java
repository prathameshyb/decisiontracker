package com.prathamesh.decisiontracker.dto;

import java.math.BigDecimal;

public class TagScoreDTO {
    public TagScoreDTO(){}

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public TagScoreDTO(Integer tagId, String tagName, Double averageScore ) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.averageScore = averageScore;

    }

    Integer tagId;
    String tagName;
    Double averageScore;
}

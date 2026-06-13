package com.prathamesh.decisiontracker.dto;

public class DecisionDueReviewDTO {
    private int decisionId;
    private String title;
    private String description;

    public int getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(int decisionId) {
        this.decisionId = decisionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeriodOverDueDays() {
        return periodOverDueDays;
    }

    public void setPeriodOverDueDays(int periodOverDueDays) {
        this.periodOverDueDays = periodOverDueDays;
    }

    private int periodOverDueDays;
}

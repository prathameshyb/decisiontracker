package com.prathamesh.decisiontracker.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Decision {

    public int getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(int decisionId) {
        this.decisionId = decisionId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int decisionId;
    private String title;
    private String description;
    private Date decisionDate;
    private String expectedOutcome;
    private String actualOutCome;
    private int score;
    private int reviewAfterDays;

    public Decision(int id,String title, String description, Date decisionDate, String expectedOutcome, String actualOutCome, int score, int reviewAfterDays) {
        this.decisionId = id;
        this.title = title;
        this.description = description;
        this.decisionDate = decisionDate;
        this.expectedOutcome = expectedOutcome;
        this.actualOutCome = actualOutCome;
        this.score = score;
        this.reviewAfterDays = reviewAfterDays;
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

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }

    public String getExpectedOutcome() {
        return expectedOutcome;
    }

    public void setExpectedOutcome(String expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }

    public String getActualOutCome() {
        return actualOutCome;
    }

    public void setActualOutCome(String actualOutCome) {
        this.actualOutCome = actualOutCome;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getReviewAfterDays() {
        return reviewAfterDays;
    }

    public void setReviewAfterDays(int reviewAfterDays) {
        this.reviewAfterDays = reviewAfterDays;
    }
}

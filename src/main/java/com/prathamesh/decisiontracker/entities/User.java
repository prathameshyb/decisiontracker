package com.prathamesh.decisiontracker.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    public List<Decision> getDecisionList() {
        return decisionList;
    }

    public void setDecisionList(List<Decision> decisionList) {
        this.decisionList = decisionList;
    }

    private String userName;

    @OneToMany(mappedBy = "user")
    List<Decision> decisionList = new ArrayList<>();

    public User(){}

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

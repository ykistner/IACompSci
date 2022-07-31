package com.example.ibcompsciia.Models;

import java.util.ArrayList;

public class User {
    private String uid;
    private String name;
    private String email;
    private String userType;
    private int participationCount;

    public User(String uid, String name, String email, String userType, int participationCount) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.participationCount = participationCount;
    }

    public User(String uid, String nameString, String emailString, int gradYearInt) {
    }

    public User(String uid, String nameString, String emailString, String adminCodeString) {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getParticipationCount() {
        return participationCount;
    }

    public void setParticipationCount(int participationCount) {
        this.participationCount = participationCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", participationCount=" + participationCount +
                '}';
    }
}

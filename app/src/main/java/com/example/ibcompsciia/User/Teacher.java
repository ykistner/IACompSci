package com.example.ibcompsciia.User;

import com.example.ibcompsciia.User.User;

public class Teacher extends User {
    private String inSchoolTitle;

    public Teacher(String uid, String name, String email, String userType, int participationCount, String inSchoolTitle) {
        super(uid, name, email, userType, participationCount);
        this.inSchoolTitle = inSchoolTitle;
    }

    public String getInSchoolTitle() {
        return inSchoolTitle;
    }

    public void setInSchoolTitle(String inSchoolTitle) {
        this.inSchoolTitle = inSchoolTitle;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "inSchoolTitle='" + inSchoolTitle + '\'' +
                '}';
    }
}

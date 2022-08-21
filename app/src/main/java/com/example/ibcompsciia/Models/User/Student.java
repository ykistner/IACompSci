package com.example.ibcompsciia.Models.User;

import java.util.ArrayList;

public class Student extends User {
    private int graduatingYear;
    private ArrayList<String> parentUIDs;

    public Student(String uid, String name, String email, String userType, int participationCount, int graduatingYear, ArrayList<String> parentUIDs) {
        super(uid, name, email, userType, participationCount);
        this.graduatingYear = graduatingYear;
        this.parentUIDs = parentUIDs;
    }

    public int getGraduatingYear() {
        return graduatingYear;
    }

    public void setGraduatingYear(int graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    public ArrayList<String> getParentUIDs() {
        return parentUIDs;
    }

    public void setParentUIDs(ArrayList<String> parentUIDs) {
        this.parentUIDs = parentUIDs;
    }

    @Override
    public String toString() {
        return "Student{" +
                "graduatingYear='" + graduatingYear + '\'' +
                ", parentUIDs=" + parentUIDs +
                '}';
    }
}

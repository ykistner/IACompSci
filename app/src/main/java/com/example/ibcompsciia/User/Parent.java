package com.example.ibcompsciia.User;

import com.example.ibcompsciia.User.User;

import java.util.ArrayList;

public class Parent extends User {
    private ArrayList<String> childrenUIDs;

    public Parent(String uid, String name, String email, String userType, int participationCount, ArrayList<String> childrenUIDs) {
        super(uid, name, email, userType, participationCount);
        this.childrenUIDs = childrenUIDs;
    }

    public ArrayList<String> getChildrenUIDs() {
        return childrenUIDs;
    }

    public void setChildrenUIDs(ArrayList<String> childrenUIDs) {
        this.childrenUIDs = childrenUIDs;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "childrenUIDs=" + childrenUIDs +
                '}';
    }
}

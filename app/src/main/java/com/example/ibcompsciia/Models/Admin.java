package com.example.ibcompsciia.Models;

public class Admin extends User{
    private String adminCode;

    public Admin(String uid, String name, String email, String userType, int participationCount, String adminCode) {
        super(uid, name, email, userType, participationCount);
        this.adminCode = adminCode;
    }

    public Admin(String uid, String nameString, String emailString, String adminCodeString) {
        super(uid, nameString, emailString, adminCodeString);
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminCode='" + adminCode + '\'' +
                '}';
    }
}

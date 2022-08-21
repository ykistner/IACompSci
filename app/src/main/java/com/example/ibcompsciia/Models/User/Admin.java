package com.example.ibcompsciia.Models.User;

public class Admin extends User {
    private String adminCode;

    public Admin(String uid, String name, String email, String userType, int participationCount, String adminCode) {
        super(uid, name, email, userType, participationCount);
        this.adminCode = adminCode;
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

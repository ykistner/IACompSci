package com.example.ibcompsciia.Models;

public class Alumni extends User{
    private int graduateYear;

    public Alumni(String uid, String name, String email, String userType, int participationCount, int graduateYear) {
        super(uid, name, email, userType, participationCount);
        this.graduateYear = graduateYear;
    }

    public int getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(int graduateYear) {
        this.graduateYear = graduateYear;
    }

    @Override
    public String toString() {
        return "Alumni{" +
                "graduateYear=" + graduateYear +
                '}';
    }
}

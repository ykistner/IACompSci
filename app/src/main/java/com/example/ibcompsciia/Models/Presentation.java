package com.example.ibcompsciia.Models;

import android.widget.EditText;

public class Presentation extends Event{
    private int gradeLevel;

    public Presentation(String eventName, String startTime, String endTime, String eventLocation, String eventId, int capacity, String topic, String organizer, int gradeLevel) {
        super(eventName, startTime, endTime, eventId, eventLocation, capacity);
        this.gradeLevel = gradeLevel;
    }

    public Presentation(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, String topic, String organizer, String eventId) {
        super(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, eventId);
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    @Override
    public String toString() {
        return "Presentation{" +
                "gradeLevel=" + gradeLevel +
                '}';
    }
}

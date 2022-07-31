package com.example.ibcompsciia.Models;

import android.widget.EditText;

public class Presentation extends Event{
    private int gradeLevel;

    public Presentation(String eventName, String eventType, String startTime, String endTime, String eventId, int capacity, int gradeLevel) {
        super(eventName, eventType, startTime, endTime, eventId, capacity);
        this.gradeLevel = gradeLevel;
    }

    public Presentation(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, EditText topic, EditText organizer, String eventId) {
        super(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, topic, organizer, eventId);
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

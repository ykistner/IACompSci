package com.example.ibcompsciia.Models.Event;

public class Presentation extends Event {
    private int gradeLevel;

    public Presentation(){
        this.gradeLevel = gradeLevel;
    }

    public Presentation(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, String topic, String organizer, String eventId) {
        super(eventNameString, "Presentation", eventStartString, eventEndString, eventLocation, eventCapacity, eventId);
        this.gradeLevel = gradeLevel;
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

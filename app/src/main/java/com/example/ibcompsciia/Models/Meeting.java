package com.example.ibcompsciia.Models;

import android.widget.EditText;

public class Meeting extends Event{
    private boolean mandatory;

    public Meeting(String eventName, String eventType, String startTime, String endTime, String eventId, int capacity, boolean mandatory) {
        super(eventName, eventType, startTime, endTime, eventId, capacity, mandatory);
        this.mandatory = mandatory;
    }

    public Meeting(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, EditText topic, EditText organizer, String eventId) {
        super(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, topic, organizer, eventId);
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "mandatory=" + mandatory +
                '}';
    }
}

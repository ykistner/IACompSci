package com.example.ibcompsciia.Models;

import android.widget.EditText;

public class VolunteerWork extends Event{
    private boolean mandatory;

    public VolunteerWork(String eventName, String eventType, String startTime, String endTime, String eventId, int capacity, boolean mandatory) {
        super(eventName, eventType, startTime, endTime, eventId, capacity);
        this.mandatory = mandatory;
    }

    public VolunteerWork(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, String causeString, String eventId) {
        super(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, eventId, causeString);
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public String toString() {
        return "VolunteerWork{" +
                "mandatory=" + mandatory +
                '}';
    }
}

package com.example.ibcompsciia.Models;

public class VolunteerWork extends Event{
    private boolean mandatory;

    public VolunteerWork(String eventName, String eventType, String startTime, String endTime, String eventId, int capacity, boolean mandatory) {
        super(eventName, eventType, startTime, endTime, eventId, capacity);
        this.mandatory = mandatory;
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

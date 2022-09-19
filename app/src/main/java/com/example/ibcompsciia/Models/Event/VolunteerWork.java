package com.example.ibcompsciia.Models.Event;

public class VolunteerWork extends Event {
    private boolean mandatory;
    
    public VolunteerWork(){
        this.mandatory = mandatory;
    }

    public VolunteerWork(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, String eventId) {
        super(eventNameString, "VolunteerWork", eventStartString, eventEndString, eventLocation, eventCapacity, eventId);
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

package com.example.ibcompsciia.Event;

import android.widget.EditText;

import com.example.ibcompsciia.Event.Event;

public class Meeting extends Event {
    private boolean mandatory;
    private String topic;
    private String organizer;

    public Meeting(String eventName, String eventType, String startTime, String endTime, int capacity, String topic, String organizer, boolean mandatory, String eventId) {
        super(eventName, startTime, eventType, endTime, capacity, eventId);
        this.mandatory = mandatory;
        this.topic = topic;
        this.organizer = organizer;
    }

    public Meeting(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, EditText topic, EditText organizer, boolean mandatory, String eventId) {
        super(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity,eventId);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
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
                ", topic='" + topic + '\'' +
                ", organizer='" + organizer + '\'' +
                '}';
    }
}

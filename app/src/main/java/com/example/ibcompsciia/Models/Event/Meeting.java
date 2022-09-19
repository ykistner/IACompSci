package com.example.ibcompsciia.Models.Event;

public class Meeting extends Event {
    private boolean mandatory;
    private String topic;
    private String organizer;

    public Meeting(){
        this.mandatory = mandatory;
        this.topic = topic;
        this.organizer = organizer;
    }


    public Meeting(String eventName, String eventType, String startTime, String endTime, int capacity, String topic, String organizer, String eventId) {
        super(eventName, "Meeting", startTime, eventType, endTime, capacity, eventId);
        this.mandatory = mandatory;
        this.topic = topic;
        this.organizer = organizer;
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

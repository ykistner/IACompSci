package com.example.ibcompsciia.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import java.io.Serializable;

public class Event implements Serializable, Parcelable {
    private String eventName;
    private String eventType;
    private String startTime;
    private String endTime;
    private String eventId;
    private int capacity;
    private boolean open;
    private String eventLocation;

    public Event(String name, String eventType, String eventName, String startTime, String endTime, String eventLocation, int capacity, String eventId) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventId = eventId;
        this.capacity = 0;
        this.open = true;
        this.eventLocation = eventLocation;
    }

    public Event(String eventName, String eventType, String startTime, String endTime, String eventId, int capacity, String eventLocation) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventId = eventId;
        this.capacity = capacity;
        this.open = true;
        this.eventLocation = eventLocation;
    }

    protected Event(Parcel in) {
        eventName = in.readString();
        eventType = in.readString();
        capacity = in.readInt();
        startTime = in.readString();
        endTime = in.readString();
        eventId = in.readString();
        open = in.readByte() != 0;
        eventLocation = in.readString();
        
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public Event(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, String topic, String organizer, String eventId) {
    }

    public Event(String eventName, String eventType, String startTime, String endTime, String eventId, int capacity) {
    }

    public Event(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, String requiredBakedGoodsString, String eventId) {
    }

    public Event(String eventNameString, String eventStartString, String eventEndString, String eventLocation, String eventCapacity, String cause, String eventId) {
    }

    public Event(EditText eventNameField, EditText eventStartField, EditText eventEndField, EditText eventCapacityField, EditText eventLocationField, EditText cause) {
    }

    public Event(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, EditText cause, String eventId) {
    }

    public Event(String eventName, String startTime, String endTime, String eventLocation, int capacity, String eventId) {
    }

    public Event(String eventName, String eventType, String startTime, String endTime, String eventId, int capacity, boolean bringBakedGoods, String lunchOrBreak) {
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", eventId='" + eventId + '\'' +
                ", capacity=" + capacity + '\'' +
                ", open =" + open +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventName);
        dest.writeString(eventType);
        dest.writeInt(capacity);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(eventId);
        dest.writeByte((byte) (open ? 1 : 0));
    }
}

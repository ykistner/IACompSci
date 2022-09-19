package com.example.ibcompsciia.Models.Event;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import com.example.ibcompsciia.Utils.Constants;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable, Parcelable {
    private String eventName;
    private String eventType;
    private String startTime;
    private String endTime;
    private String eventId;
    private int capacity;
    private int remainingCapacity;
    private boolean open;
    private String eventLocation;
    private ArrayList<String> participantsUIDs;

    public Event(){
        this.eventName = Constants.NOTHING; //contsants make all "n/a"
        this.eventType = Constants.NOTHING;
        this.startTime = Constants.NOTHING;
        this.endTime = Constants.NOTHING;
        this.eventId = Constants.NOTHING;
        this.capacity = 0;
        this.open = true;
        this.eventLocation = Constants.NOTHING;

        participantsUIDs = new ArrayList<>();
    }

    public Event(String eventName, String eventType, String startTime, String endTime, String eventLocation, int capacity, String eventId) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventId = eventId;
        this.capacity = capacity;
        this.remainingCapacity = capacity;
        this.open = true;
        this.eventLocation = eventLocation;

        participantsUIDs = new ArrayList<>();
    }

    protected Event(Parcel in) {
        eventName = in.readString();
        eventType = in.readString();
        capacity = in.readInt();
        remainingCapacity = in.readInt();
        startTime = in.readString();
        endTime = in.readString();
        eventId = in.readString();
        open = in.readByte() != 0;
        eventLocation = in.readString();
        participantsUIDs = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventName);
        dest.writeString(eventType);
        dest.writeInt(capacity);
        dest.writeInt(remainingCapacity);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(eventId);
        dest.writeByte((byte) (open ? 1 : 0));
        dest.writeString(eventLocation);
        dest.writeStringList(participantsUIDs);
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

    public int getRemainingCapacity(){
        return remainingCapacity;
    }

    public void setRemainingCapacity(int remainingCapacity){
        this.remainingCapacity = remainingCapacity;
    }

    public ArrayList<String> getParticipantsUIDs(){ return participantsUIDs; }

    public void setParticipantsUIDs(ArrayList<String> participantsUIDs){
        this.participantsUIDs = participantsUIDs;
    }

    public void addReservedUid(String uid){
        participantsUIDs.add(uid);

    }

    //bring baked goods get and set

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

}

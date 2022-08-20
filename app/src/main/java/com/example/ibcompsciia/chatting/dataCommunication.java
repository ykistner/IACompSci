package com.example.ibcompsciia.chatting;

public class dataCommunication {
    private String key;

    private String from;
    private String message;
    private long time;
    private String type;
    private String date;

    public dataCommunication(){
    }

    public dataCommunication(String key, String from, String message, String date, long time, String type) {
        this.key = key;
        this.from = from;
        this.message = message;
        this.time = time;
        this.date = date;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

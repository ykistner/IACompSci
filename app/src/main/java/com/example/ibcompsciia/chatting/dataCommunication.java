package com.example.ibcompsciia.chatting;

public class dataCommunication {
    private String key;

    private String from;
    private String message;
    private String time;
    private String type;

    public dataCommunication(String from, String message, String time, String type) {
        this.from = from;
        this.message = message;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

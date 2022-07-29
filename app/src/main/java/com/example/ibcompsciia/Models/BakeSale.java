package com.example.ibcompsciia.Models;

public class BakeSale extends Event {
    private boolean bringBakedGoods;
    private String lunchOrBreak;

    public BakeSale(String eventName, String eventType, String startTime, String endTime, String eventId, int capacity, boolean bringBakedGoods, String lunchOrBreak) {
        super(eventName, eventType, startTime, endTime, eventId, capacity);
        this.bringBakedGoods = bringBakedGoods;
        this.lunchOrBreak = lunchOrBreak;
    }

    public boolean isBringBakedGoods() {
        return bringBakedGoods;
    }

    public void setBringBakedGoods(boolean bringBakedGoods) {
        this.bringBakedGoods = bringBakedGoods;
    }

    public String getLunchOrBreak() {
        return lunchOrBreak;
    }

    public void setLunchOrBreak(String lunchOrBreak) {
        this.lunchOrBreak = lunchOrBreak;
    }

    @Override
    public String toString() {
        return "BakeSale{" +
                "bringBakedGoods=" + bringBakedGoods +
                ", lunchOrBreak='" + lunchOrBreak + '\'' +
                '}';
    }
}

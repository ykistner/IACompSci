package com.example.ibcompsciia.Models.Event;

public class BakeSale extends Event {
    private boolean bringBakedGoods;
    private String lunchOrBreak;

    public BakeSale(){
        this.bringBakedGoods = bringBakedGoods;
        this.lunchOrBreak = lunchOrBreak;
    }

    public BakeSale(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, String eventId) {
        super(eventNameString, "Bak" +
                "eSale", eventStartString, eventEndString, eventLocation, eventCapacity, eventId);
        this.bringBakedGoods = true;
        this.lunchOrBreak = "test";
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
                ", lunchOrBreak='" + lunchOrBreak + '\'' + super.toString() +
                '}';
    }
}

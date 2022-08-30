package com.example.ibcompsciia.Models.Event;

public class BakeSale extends Event {
    private boolean bringBakedGoods;

    public BakeSale(){
        this.bringBakedGoods = bringBakedGoods;
    }

    public BakeSale(String eventNameString, String eventStartString, String eventEndString, String eventLocation, int eventCapacity, String eventId) {
        super(eventNameString, "BakeSale", eventStartString, eventEndString, eventLocation, eventCapacity, eventId);
        this.bringBakedGoods = true;
    }

    public boolean isBringBakedGoods() {
        return bringBakedGoods;
    }

    public void setBringBakedGoods(boolean bringBakedGoods) {
        this.bringBakedGoods = bringBakedGoods;
    }

    @Override
    public String toString() {
        return "BakeSale{" +
                "bringBakedGoods=" + bringBakedGoods + + '\'' + super.toString() +
                '}';
    }
}

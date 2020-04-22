package com.ict.ex72_recyclervlew;

public class VO {
       String name ;
       String price;
       String event ;
       int resId;

    public VO(String name, String price, String event, int resId) {
        this.name = name;
        this.price = price;
        this.event = event;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}

package com.example.ex18_listview;

public class VO {
    private int resID;
    private  String imgName;

    public VO() {
    }

    public VO(int resID, String imgName) {
        this.resID = resID;
        this.imgName = imgName;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}

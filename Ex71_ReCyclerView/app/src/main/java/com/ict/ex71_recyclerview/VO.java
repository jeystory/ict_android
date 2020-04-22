package com.ict.ex71_recyclerview;

public class VO {
    String name;
    String birth;
    String phone;
    int resld ;

    public VO(String name, String birth, String phone) {
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        resld = R.mipmap.ic_launcher_round;
    }

    public VO(String name, String birth, String phone, int resld) {
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.resld = resld;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getResld() {
        return resld;
    }

    public void setResld(int resld) {
        this.resld = resld;
    }
}

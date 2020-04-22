package com.example.ex79_socket;

public class VO {
    private String local, desc, ta, icon;

    public VO() {
    }

    public VO(String local, String desc, String ta, String icon) {
        this.local = local;
        this.desc = desc;
        this.ta = ta;
        this.icon = icon;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

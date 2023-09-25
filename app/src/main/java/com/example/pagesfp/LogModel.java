package com.example.pagesfp;

public class LogModel {
    private String lid, lname, ltimestamp;

    // constructor

    public LogModel(String lid, String lname, String ltimestamp) {
        this.lid = lid;
        this.lname = lname;
        this.ltimestamp = ltimestamp;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLtimestamp() {
        return ltimestamp;
    }

    public void setLtimestamp(String ltimestamp) {
        this.ltimestamp = ltimestamp;
    }
}

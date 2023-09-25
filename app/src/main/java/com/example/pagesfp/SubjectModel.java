package com.example.pagesfp;

public class SubjectModel {
    private String sid, sname, scode, sclass, stime;

    // constructor

    public SubjectModel(String sid, String sname, String scode, String sclass, String stime) {
        this.sid = sid;
        this.sname = sname;
        this.scode = scode;
        this.sclass = sclass;
        this.stime = stime;
    }

    // create getter setter

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }
}

package com.example.pagesfp.server;

import com.google.gson.annotations.SerializedName;

public class ResponseApi {
//    private Integer id;
    private String nrp;
    private String pass;
    private String image;
    private String status;

    public String getNrp() {
        return nrp;
    }

//    public String getId() {
//        return id;
//    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public String getPass() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResponseApi(String nrp, String status, String pass ,String image) {
        this.nrp = nrp;
        this.pass = pass;
        this.image = image;
        this.status = status;
    }
}

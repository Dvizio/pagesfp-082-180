package com.example.pagesfp.server;

import com.google.gson.annotations.SerializedName;

public class ResponseApi {
    @SerializedName("msg")
    String msg;

    public String getMsg() {
        return msg;
    }
}

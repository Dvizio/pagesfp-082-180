package com.example.pagesfp.server;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiClientAttendance {
    @FormUrlEncoded
    @POST("/kirimFoto.php/")
    Call<ResponseApi> kirim(@Field("idUser") String id,
                            @Field("status") String status,
                            @Field("password") String pass,
                            @Field("image") String image);
}

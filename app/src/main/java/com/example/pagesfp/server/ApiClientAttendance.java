package com.example.pagesfp.server;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiClientAttendance {
    @POST("/sendImage")
    Call<ResponseApi> kirim(@Body ResponseApi responseApi);


//    @FormUrlEncoded
//    @Headers("Content-Type: application/json")
//    @POST("/sendImage")
//    Call<ResponseApi> kirim(@Field("idUser") String id,
//                            @Field("status") String status,
//                            @Field("password") String pass,
//                            @Field("image") String image);
}

package com.example.viewflipper_cricleindicator;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("newimagesmanager.php")  // API lấy danh sách ảnh
    Call<MessageModel> loadImageSlider(@Field("position") int position);
}



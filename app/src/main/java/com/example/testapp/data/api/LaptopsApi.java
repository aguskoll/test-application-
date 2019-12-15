package com.example.testapp.data.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LaptopsApi {

    @GET("list")
    Call<ResponseBody> getLaptops();
}

package com.example.testapp.data.service;

import com.example.testapp.data.api.LaptopsApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ApiBuilder {

    public static LaptopsApi getLaptosApi() {
        return getRetrofitBuilder().create(LaptopsApi.class);
    }

    private static Retrofit getRetrofitBuilder() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://private-f0eea-mobilegllatam.apiary-mock.com/");
        return builder.client(
                okHttpClient.newBuilder().build()
        ).build();
    }
}

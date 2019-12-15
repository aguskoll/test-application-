package com.example.testapp.data.service;

import com.example.testapp.data.entity.Laptop;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaptopServices {
    private PublishSubject<List<Laptop>> observable = PublishSubject.create();

    public Observable<List<Laptop>> getLaptops() {
        Call<ResponseBody> call = ApiBuilder.getLaptosApi().getLaptops();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Gson gson = new Gson();

                    Laptop[] laptops = null;
                    List<Laptop> laptopList = new ArrayList<>();
                    try {
                        laptops = gson.fromJson(response.body().string(), Laptop[].class);
                        Collections.addAll(laptopList, laptops);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    observable.onNext(laptopList);
                }
                else{
                    notifyError(new Throwable());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                notifyError(t);
            }
        });
        return observable;
    }

    private void notifyError(Throwable throwable) {
        observable.onError(throwable);
        observable.onComplete();
    }

}

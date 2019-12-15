package com.example.testapp.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.testapp.R;
import com.example.testapp.data.entity.Laptop;
import com.example.testapp.data.repository.LaptopRepository;
import com.example.testapp.data.service.LaptopServices;
import com.example.testapp.domain.GetLaptopsUseCase;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements LaptopAdapter.listener {

    private RecyclerView laptopList;
    private LaptopAdapter adapter;

    // todo: move the GetLaptopsUseCase and logic to a view model
    private GetLaptopsUseCase getLaptopsUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        laptopList = findViewById(R.id.rv_laptop_list);
        adapter = new LaptopAdapter(new ArrayList<>(), this);
        laptopList.setAdapter(adapter);
        laptopList.setLayoutManager(new LinearLayoutManager(this));
        initLaptopUseCase();
        updateUI();
    }

    private void updateUI() {
        getLaptopsUseCase.getLaptops()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    throwable.printStackTrace();
                })
                .subscribe(items -> {
                    adapter.updateList(items);
                });
    }

    // todo: use a factory or dependency injection with dagger
    private void initLaptopUseCase() {
        getLaptopsUseCase = new GetLaptopsUseCase(new LaptopRepository(new LaptopServices()));
    }

    @Override
    public void onClick(Laptop laptop) {
        //Intent intent = new Intent(this, PersonDetailsActivity.class);
        //intent.putExtra(EXTRA_ID, personId);
        //startActivity(intent);
    }
}

package com.example.testapp.data.repository;

import com.example.testapp.data.entity.Laptop;
import com.example.testapp.data.service.LaptopServices;

import java.util.List;

import io.reactivex.Observable;

public class LaptopRepository {

    private LaptopServices laptopServices;

    public LaptopRepository(LaptopServices laptopServices){
        this.laptopServices = laptopServices;
    }

    // todo: Agustin.Koll here we can store the laptops in a DB and check if we already have them store
    public Observable<List<Laptop>> getLaptops(){
        return laptopServices.getLaptops();
    }
}

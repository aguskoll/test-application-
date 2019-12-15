package com.example.testapp.domain;

import com.example.testapp.data.entity.Laptop;
import com.example.testapp.data.repository.LaptopRepository;

import java.util.List;

import io.reactivex.Observable;

public class GetLaptopsUseCase {

    private LaptopRepository laptopRepository;

    public GetLaptopsUseCase(LaptopRepository laptopRepository){
        this.laptopRepository = laptopRepository;
    }

    public Observable<List<Laptop>> getLaptops(){
        return laptopRepository.getLaptops();
    }
}

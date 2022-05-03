package com.dackow.recruitmenttask.controllers;


import com.dackow.recruitmenttask.models.dtos.CarDto;
import com.dackow.recruitmenttask.repositories.CarRepository;
import com.dackow.recruitmenttask.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;
    private final CarService carService;

    @GetMapping
    ResponseEntity<List<CarDto>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<CarDto> getSingleCar(@PathVariable Long id) {
        return new ResponseEntity<>(carService.getById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Void> addSingleCar(@RequestBody CarDto carDto) {
        carService.addCar(carDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> removeCar(@PathVariable long id) {
        carRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto) {
        CarDto saved = carService.updateCar(carDto);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
}
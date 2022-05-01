package com.dackow.recruitmenttask.controllers;


import com.dackow.recruitmenttask.models.dtos.CarDto;
import com.dackow.recruitmenttask.models.entities.Car;
import com.dackow.recruitmenttask.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

//    private List<CarDto> carDtos = new ArrayList<>();

    @GetMapping
    ResponseEntity getAllCars() {
        return new ResponseEntity(carRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity getSingleCar(@PathVariable long id) {
        return carRepository.findById(id)
                .map(car -> new ResponseEntity(car, HttpStatus.OK))
                .orElse(new ResponseEntity("There is no car with id " + id, HttpStatus.BAD_REQUEST));
    }

    @PostMapping
    ResponseEntity addSingleCar(@RequestBody CarDto carDto) {
        Car car = Car.builder()
                .doors(carDto.getDoors())
                .mark(carDto.getMark())
                .model(carDto.getModel())
                .mark(carDto.getMark())
                .owner(carDto.getOwner())
                .works(carDto.isWorks())
                .build();
        carRepository.save(car);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity removeCar(@PathVariable long id) {
        carRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
//        Optional<CarDto> founded = carDtos.stream()
//                .filter(carDto -> carDto.getId() == id)
//                .findAny();
//        if (founded.isPresent()) {
//            carDtos.remove(founded.get());
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity("There is no car with id " + id, HttpStatus.BAD_REQUEST);
//        }
    }

    @PutMapping
    ResponseEntity updateCar(@RequestBody CarDto carDto) {
        Optional<Car> founded = carRepository.findById(carDto.getId());
        if (founded.isEmpty()) {
            return new ResponseEntity("There is no car with id " + carDto.getId(), HttpStatus.BAD_REQUEST);
        }
        Car car = founded.get();
        car.setDoors(carDto.getDoors());
        car.setMark(carDto.getMark());
        car.setModel(carDto.getModel());
        car.setMark(carDto.getMark());
        car.setOwner(carDto.getOwner());
        car.setWorks(carDto.isWorks());
//        carDtos.stream()
//                .filter(c -> c.getId() == carDto.getId())
//                .findAny()
//                .ifPresentOrElse(
//                        c -> {
//                            c.setDoors(carDto.getDoors());
//                            c.setMark(carDto.getMark());
//                            c.setModel(carDto.getModel());
//                            c.setMark(c.getMark());
//                            c.setOwner(carDto.getOwner());
//                            c.setWorks(carDto.isWorks());
//                        },
//                        () -> new ResponseEntity("There is no car with id " + carDto.getId(), HttpStatus.BAD_REQUEST));
        return new ResponseEntity(HttpStatus.OK);
    }
}
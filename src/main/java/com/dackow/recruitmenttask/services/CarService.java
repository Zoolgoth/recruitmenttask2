package com.dackow.recruitmenttask.services;

import com.dackow.recruitmenttask.models.dtos.CarDto;
import com.dackow.recruitmenttask.models.entities.Car;
import com.dackow.recruitmenttask.repositories.CarRepository;
import com.dackow.recruitmenttask.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final OwnerRepository ownerRepository;

    public List<CarDto> getAllCars() {
        List<Car> allCars = carRepository.findAll();
        return allCars.stream()
                .map(carEntity -> modelMapper.map(carEntity, CarDto.class))
                .collect(Collectors.toList());
    }

    public Optional<CarDto> getById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        CarDto carDto = null;
        if (car.isPresent()) {
            carDto = modelMapper.map(car.get(), CarDto.class);
        }
        return Optional.ofNullable(carDto);
    }

    public void addCar(CarDto carDto) {
        Car car = modelMapper.map(carDto, Car.class);
        carRepository.save(car);
    }

    public CarDto updateCar(CarDto carDto) {
        Optional<Car> founded = carRepository.findById(carDto.getId());
        Car car = founded.get();
        car.setDoors(carDto.getDoors());
        car.setMark(carDto.getMark());
        car.setModel(carDto.getModel());
        car.setMark(carDto.getMark());
        car.setOwner(ownerRepository.getById(carDto.getOwnerId()));
        car.setWorks(carDto.isWorks());
        carRepository.save(car);
        return modelMapper.map(car, CarDto.class);
    }
}

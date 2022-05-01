package com.dackow.recruitmenttask.models.converters;

import com.dackow.recruitmenttask.models.dtos.CarDto;
import com.dackow.recruitmenttask.models.entities.Car;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class CarToCarDtoConverter implements Converter<Car, CarDto> {
    @Override
    public CarDto convert(MappingContext<Car, CarDto> mappingContext) {
        Car car = mappingContext.getSource();
        return CarDto.builder()
                .id(car.getId())
                .doors(car.getDoors())
                .mark(car.getMark())
                .model(car.getModel())
                .mark(car.getMark())
                .ownerId(car.getOwner().getId())
                .works(car.isWorks())
                .build();
    }
}
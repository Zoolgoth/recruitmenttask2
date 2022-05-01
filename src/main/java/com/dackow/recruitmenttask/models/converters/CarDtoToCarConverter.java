package com.dackow.recruitmenttask.models.converters;

import com.dackow.recruitmenttask.models.dtos.CarDto;
import com.dackow.recruitmenttask.models.entities.Car;
import com.dackow.recruitmenttask.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarDtoToCarConverter implements Converter<CarDto, Car> {

    private final OwnerRepository ownerRepository;

    @Override
    public Car convert(MappingContext<CarDto, Car> mappingContext) {
        CarDto carDto = mappingContext.getSource();
        return Car.builder()
                .doors(carDto.getDoors())
                .mark(carDto.getMark())
                .model(carDto.getModel())
                .mark(carDto.getMark())
                .owner(ownerRepository.getById(carDto.getOwnerId()))
                .works(carDto.isWorks())
                .build();
    }
}
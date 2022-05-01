package com.dackow.recruitmenttask.models.converters;

import com.dackow.recruitmenttask.models.dtos.OwnerDto;
import com.dackow.recruitmenttask.models.entities.Car;
import com.dackow.recruitmenttask.models.entities.Owner;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OwnerToOwnerDtoConverter implements Converter<Owner, OwnerDto> {
    @Override
    public OwnerDto convert(MappingContext<Owner, OwnerDto> mappingContext) {
        Owner owner = mappingContext.getSource();
        return OwnerDto.builder()
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .id(owner.getId())
                .carsId(owner.getCars().stream().map(Car::getId).collect(Collectors.toList()))
                .build();
    }
}
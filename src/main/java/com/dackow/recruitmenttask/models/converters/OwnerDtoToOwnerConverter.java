package com.dackow.recruitmenttask.models.converters;

import com.dackow.recruitmenttask.models.dtos.OwnerDto;
import com.dackow.recruitmenttask.models.entities.Owner;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class OwnerDtoToOwnerConverter implements Converter<OwnerDto, Owner> {
    @Override
    public Owner convert(MappingContext<OwnerDto, Owner> mappingContext) {
        OwnerDto ownerDto = mappingContext.getSource();
        return Owner.builder()
                .firstName(ownerDto.getFirstName())
                .lastName(ownerDto.getLastName())
                .build();
    }
}

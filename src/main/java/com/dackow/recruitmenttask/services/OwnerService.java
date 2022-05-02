package com.dackow.recruitmenttask.services;

import com.dackow.recruitmenttask.models.dtos.CarDto;
import com.dackow.recruitmenttask.models.dtos.OwnerDto;
import com.dackow.recruitmenttask.models.entities.Owner;
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
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;

    public List<OwnerDto> getAllOwners() {
        List<Owner> allOwners = ownerRepository.findAll();
        return allOwners.stream()
                .map(ownerEntity -> modelMapper.map(ownerEntity, OwnerDto.class))
                .collect(Collectors.toList());
    }

    public Optional<OwnerDto> getById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        OwnerDto ownerDto = null;
        if (owner.isPresent()) {
            ownerDto = modelMapper.map(owner.get(), OwnerDto.class);
        }
        return Optional.ofNullable(ownerDto);
    }

    public void addOwner(OwnerDto ownerDto) {
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        ownerRepository.save(owner);
    }

    public CarDto updateOwner(OwnerDto ownerDto) {
        Optional<Owner> founded = ownerRepository.findById(ownerDto.getId());
        Owner owner = founded.get();
//        owner.getId(ownerDto.getLastName());


        ownerRepository.save(owner);
//        return modelMapper.map(owner, OwnerDto.class);
    }

}
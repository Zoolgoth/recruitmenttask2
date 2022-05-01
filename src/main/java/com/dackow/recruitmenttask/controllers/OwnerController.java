package com.dackow.recruitmenttask.controllers;

import com.dackow.recruitmenttask.models.dtos.OwnerDto;
import com.dackow.recruitmenttask.models.entities.Owner;
import com.dackow.recruitmenttask.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    ResponseEntity<List<OwnerDto>> getAllOwners() {
        List<Owner> owners = ownerRepository.findAll();
        List<OwnerDto> allOwners = owners.stream()
                .map(ownerEntity -> modelMapper.map(ownerEntity, OwnerDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(allOwners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity getSingleOwner(@PathVariable long id) {
        return ownerRepository.findById(id)
                .map(owner -> new ResponseEntity(owner, HttpStatus.OK))
                .orElse(new ResponseEntity("There is no owner with id " + id, HttpStatus.BAD_REQUEST));
    }

    @PostMapping
    ResponseEntity addOwner(@RequestBody OwnerDto ownerDto) {
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        ownerRepository.save(owner);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity removeOwner(@PathVariable long id) {
        ownerRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    ResponseEntity updateCar(@RequestBody OwnerDto ownerDto) {
        Optional<Owner> founded = ownerRepository.findById(ownerDto.getId());
        if (founded.isEmpty()) {
            return new ResponseEntity("There is no owner with id " + ownerDto.getId(), HttpStatus.BAD_REQUEST);
        }
        Owner owner = founded.get();
        Optional.ofNullable(ownerDto.getFirstName()).ifPresent(owner::setFirstName);
        Optional.ofNullable(ownerDto.getLastName()).ifPresent(owner::setLastName);
        ownerRepository.save(owner);
        return new ResponseEntity(HttpStatus.OK);
    }
}

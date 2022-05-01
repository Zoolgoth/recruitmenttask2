package com.dackow.recruitmenttask.models.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarDto {

    private long id;
    private int doors;
    private String mark;
    private String model;
    private String owner;
    private boolean works;
}

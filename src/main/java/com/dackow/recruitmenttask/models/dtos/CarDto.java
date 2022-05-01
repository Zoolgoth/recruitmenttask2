package com.dackow.recruitmenttask.models.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {
    private long id;
    private int doors;
    private String mark;
    private String model;
    private Long ownerId;
    private boolean works;
}

package com.dackow.recruitmenttask.models.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OwnerDto {
    private long id;
    private String firstName;
    private String lastName;
    private List<Long> carsId;
}
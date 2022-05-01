package com.dackow.recruitmenttask.models.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int doors;
    private String mark;
    private String model;
    @ManyToOne
    private Owner owner;
    private boolean works;
}

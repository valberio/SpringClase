package com.example.springboot;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String razaPaciente;
    @Column
    String nombrePaciente;
    @Column
    LocalDateTime fechaTurno;
}
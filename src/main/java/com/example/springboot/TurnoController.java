package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoRepository turnoRepository;

    // Get all turnos
    @GetMapping
    public ResponseEntity<List<Turno>> getAllTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        return ResponseEntity.ok(turnos);
    }
}

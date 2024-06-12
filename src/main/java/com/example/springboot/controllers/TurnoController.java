package com.example.springboot.controllers;

import com.example.springboot.repositories.TurnoRepository;
import com.example.springboot.models.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> obtenerTurnoPorId(@PathVariable Long id){
        Optional<Turno> t = turnoRepository.findById(id);
        return ResponseEntity.ok(t);
    }

    @PostMapping
    public ResponseEntity<Turno> createTurno(@RequestBody Turno turno) {
        Turno savedTurno = turnoRepository.save(turno);
        return new ResponseEntity<>(savedTurno, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable Long id) {
        Optional<Turno> optionalTurno = turnoRepository.findById(id);
        if (optionalTurno.isPresent()) {
            turnoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

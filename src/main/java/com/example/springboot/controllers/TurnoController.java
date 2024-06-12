package com.example.springboot.controllers;

import com.example.springboot.exceptions.NotFoundException;
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
public class TurnoController{

    @Autowired
    private TurnoRepository turnoRepository;

    @GetMapping
    public ResponseEntity<List<Turno>> obtenerTurnos(){
        List<Turno> turnos = turnoRepository.findAll();
        return ResponseEntity.ok(turnos);
    }

    @PostMapping
    public ResponseEntity<Void> cargarTurno(@RequestBody Turno turno){
        Turno t = turnoRepository.save(turno);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> obtenerTurnoPorId(@PathVariable Long id){
        Turno turnoBuscado = this.buscarTurno(id);
        return ResponseEntity.ok(turnoBuscado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id){
        Turno turnoBuscado = this.buscarTurno(id);
        turnoRepository.delete(turnoBuscado);
        return ResponseEntity.ok().build();
    }

    private Turno buscarTurno(Long id) {
        Optional<Turno> posibleTurno = turnoRepository.findById(id);

        if(posibleTurno.isEmpty())
            throw new NotFoundException("No existe un turno de id " + id);

        return posibleTurno.get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable Long id,
                                             @RequestParam(name = "raza_paciente") String razaPaciente,
                                             @RequestParam(required = true) String nombrePaciente) {
        Turno existingTurno = buscarTurno(id);
        existingTurno.setNombrePaciente(nombrePaciente);
        existingTurno.setRazaPaciente(razaPaciente);
        Turno savedTurno = turnoRepository.save(existingTurno);
        return ResponseEntity.ok(savedTurno);
    }


}





/*
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
        if (t.isPresent()){
            return ResponseEntity.ok(t);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createTurno(@RequestBody Turno turno) {
        Turno savedTurno = turnoRepository.save(turno);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable Long id) {
        Optional<Turno> optionalTurno = turnoRepository.findById(id);
        if (optionalTurno.isPresent()) {
            turnoRepository.deleteById(id);
            return ResponseEntity.noContent().build();

        } else {
            ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable Long id, @RequestBody Turno updatedTurno) {
        Optional<Turno> optionalTurno = turnoRepository.findById(id);
        if (optionalTurno.isPresent()) {
            Turno existingTurno = optionalTurno.get();
            existingTurno.setNombre(updatedTurno.getNombre());
            existingTurno.setDescripcion(updatedTurno.getDescripcion());
            // Actualizar otros campos necesarios
            Turno savedTurno = turnoRepository.save(existingTurno);
            return ResponseEntity.ok(savedTurno);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
*/
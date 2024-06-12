package com.example.springboot.repositories;

import com.example.springboot.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}

*/

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long>{
}
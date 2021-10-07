package com.petclinicrest.repositories;

import com.petclinicrest.entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Integer> {
}

package com.petclinicrest.repositories;

import com.petclinicrest.entities.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {
}

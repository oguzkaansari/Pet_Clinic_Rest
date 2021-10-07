package com.petclinicrest.dto;

import com.petclinicrest.repositories.StatisticsRepository;
import com.petclinicrest.utils.Result;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatisticsDto {

    final StatisticsRepository sRepo;

    public StatisticsDto(StatisticsRepository sRepo) {
        this.sRepo = sRepo;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, sRepo.count(), "", sRepo.findAll());
        return result.resultMap;
    }

}

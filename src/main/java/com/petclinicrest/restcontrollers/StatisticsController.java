package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.StatisticsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    final StatisticsDto sDto;

    public StatisticsController(StatisticsDto sDto) {
        this.sDto = sDto;
    }

    @GetMapping("/list/all")
    public Map<String, Object> list(){
        return sDto.list();
    }
}

package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.StaticDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/static")
public class StaticController {

    final StaticDto sDto;

    public StaticController(StaticDto sDto) {
        this.sDto = sDto;
    }

    @GetMapping("/list/genders")
    public Map<String, Object> listGenders(){
        return sDto.listGenders();
    }

    @GetMapping("/list/pay_types")
    public Map<String, Object> listPayTypes(){
        return sDto.listPayTypes();
    }

    @GetMapping("/list/product_types")
    public Map<String, Object> listProductTypes(){
        return sDto.listProductTypes();
    }

    @GetMapping("/list/races")
    public Map<String, Object> listRaces(){
        return sDto.listRaces();
    }

    @GetMapping("/list/specs")
    public Map<String, Object> listSpecs(){
        return sDto.listSpecs();
    }

    @GetMapping("/list/roles")
    public Map<String, Object> listRoles(){
        return sDto.listRoles();
    }

    @GetMapping("/list/taxes")
    public Map<String, Object> listTaxes(){
        return sDto.listTaxes();
    }

    @GetMapping("/list/test_types")
    public Map<String, Object> listTestTypes(){
        return sDto.listTestTypes();
    }

    @GetMapping("/list/units")
    public Map<String, Object> listUnits(){
        return sDto.listUnits();
    }

    @GetMapping("/list/active_status")
    public Map<String, Object> listActiveStatus(){
        return sDto.listActiveStatus();
    }

    @GetMapping("/list/pay_status")
    public Map<String, Object> listPayStatus(){
        return sDto.listPayStatus();
    }

    @GetMapping("/list/test_status")
    public Map<String, Object> listTestStatus(){
        return sDto.listTestStatus();
    }


}

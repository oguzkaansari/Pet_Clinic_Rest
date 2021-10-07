package com.petclinicrest.dto;

import com.petclinicrest.repositories.*;
import com.petclinicrest.utils.Result;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StaticDto {

    final GenderRepository genderRepo;
    final PayTypeRepository payTypeRepo;
    final ProductTypeRepository productTypeRepo;
    final RaceRepository raceRepo;
    final SpecRepository specRepo;
    final RoleRepository roleRepo;
    final TaxRepository taxRepo;
    final TestTypeRepository testTypeRepo;
    final UnitRepository unitRepo;
    final ActiveStatusRepository activeRepo;
    final PayStatusRepository payStatusRepo;
    final TestStatusRepository testStatusRepo;

    public StaticDto(GenderRepository genderRepo, PayTypeRepository paytypeRepo, ProductTypeRepository productTypeRepo, RaceRepository raceRepo, SpecRepository specRepo, RoleRepository roleRepo, TaxRepository taxRepo, TestTypeRepository testtypeRepo, UnitRepository unitRepo, ActiveStatusRepository activeRepo, PayStatusRepository payStatusRepo, TestStatusRepository testStatusRepo) {
        this.genderRepo = genderRepo;
        this.payTypeRepo = paytypeRepo;
        this.productTypeRepo = productTypeRepo;
        this.raceRepo = raceRepo;
        this.specRepo = specRepo;
        this.roleRepo = roleRepo;
        this.taxRepo = taxRepo;
        this.testTypeRepo = testtypeRepo;
        this.unitRepo = unitRepo;
        this.activeRepo = activeRepo;
        this.payStatusRepo = payStatusRepo;
        this.testStatusRepo = testStatusRepo;
    }

    @Cacheable(cacheNames = "genders")
    public Map<String, Object> listGenders(){
        Result result = new Result(true, 0, genderRepo.count(), "", genderRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "pay_types")
    public Map<String, Object> listPayTypes(){
        Result result = new Result(true, 0, payTypeRepo.count(), "", payTypeRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "product_types")
    public Map<String, Object> listProductTypes(){
        Result result = new Result(true, 0, payTypeRepo.count(), "", productTypeRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "races")
    public Map<String, Object> listRaces(){
        Result result = new Result(true, 0, raceRepo.count(), "", raceRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "specs")
    public Map<String, Object> listSpecs(){
        Result result = new Result(true, 0, raceRepo.count(), "", specRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "roles")
    public Map<String, Object> listRoles(){
        Result result = new Result(true, 0, roleRepo.count(), "", roleRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "taxes")
    public Map<String, Object> listTaxes(){
        Result result = new Result(true, 0, taxRepo.count(), "", taxRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "test_types")
    public Map<String, Object> listTestTypes(){
        Result result = new Result(true, 0, testTypeRepo.count(), "", testTypeRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "units")
    public Map<String, Object> listUnits(){
        Result result = new Result(true, 0, unitRepo.count(), "", unitRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "active_status")
    public Map<String, Object> listActiveStatus(){
        Result result = new Result(true, 0, unitRepo.count(), "", activeRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "pay_status")
    public Map<String, Object> listPayStatus(){
        Result result = new Result(true, 0, unitRepo.count(), "", payStatusRepo.findAll());
        return result.resultMap;
    }

    @Cacheable(cacheNames = "test_status")
    public Map<String, Object> listTestStatus(){
        Result result = new Result(true, 0, unitRepo.count(), "", testStatusRepo.findAll());
        return result.resultMap;
    }
}

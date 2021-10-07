package com.petclinicrest.dto;

import com.petclinicrest.entities.Supplier;
import com.petclinicrest.repositories.SupplierRepository;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class SupplierDto {

    final SupplierRepository sRepo;

    public SupplierDto(SupplierRepository sRepo) {
        this.sRepo = sRepo;
    }

    public Map<String, Object> find(Integer id){
        Optional<Supplier> supplier = sRepo.findById(id);
        Result result;
        if(supplier.isPresent()){
            result = new Result(true, 0, sRepo.count(), "", supplier);
        }else{
            result = new Result(false, 0, sRepo.count(), Messages.findSupplierFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, sRepo.count(), "", sRepo.findAll());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<Supplier> supplierPage = sRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, supplierPage.getTotalPages(), sRepo.count(), "", supplierPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, sRepo.count(), "", sRepo.searchByKey(key));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<Supplier> supplierPage = sRepo.searchByKey(key, PageRequest.of(page, size));
        Result result = new Result(true, supplierPage.getTotalPages(), sRepo.count(), "", supplierPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> save(Supplier supplier){
        Result result;
        if(supplier.getId() == null || supplier.getId() == 0){
            result = new Result(true, 0, sRepo.count(), Messages.saveSuccessMessage, sRepo.save(supplier));
        }else{
            result = new Result(true, 0, sRepo.count(), Messages.updateSuccessMessage, sRepo.saveAndFlush(supplier));
        }
        return result.resultMap;
    }

}

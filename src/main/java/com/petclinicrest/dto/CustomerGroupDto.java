package com.petclinicrest.dto;

import com.petclinicrest.entities.Customer;
import com.petclinicrest.entities.CustomerGroup;
import com.petclinicrest.repositories.CustomerGroupRepository;
import com.petclinicrest.repositories.CustomerRepository;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CustomerGroupDto {

    final CustomerGroupRepository cgRepo;
    final CustomerRepository cRepo;

    public CustomerGroupDto(CustomerGroupRepository cgRepo, CustomerRepository cRepo) {
        this.cgRepo = cgRepo;
        this.cRepo = cRepo;
    }

    public Map<String, Object> find(Integer id){
        Optional<CustomerGroup> customerGroup = cgRepo.findById(id);
        Result result;
        if(customerGroup.isPresent()){
            result = new Result(true, 0, cgRepo.count(), "", customerGroup);
        }else{
            result = new Result(false, 0, cgRepo.count(), Messages.findGroupFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, cgRepo.count(), "", cgRepo.findAll());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<CustomerGroup> customerGroupPage = cgRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, customerGroupPage.getTotalPages(), cgRepo.count(), "", customerGroupPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listCustomers(Integer id){
        Result result = new Result(true, 0, cRepo.count(), "", cRepo.findByGroup_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listCustomers(Integer id, Integer page, Integer size){
        Page<Customer> customerPage = cRepo.findByGroup_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, customerPage.getTotalPages(), cRepo.count(), "", customerPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, cgRepo.count(), "", cgRepo.searchByKey(key));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<CustomerGroup> customerGroupPage = cgRepo.searchByKey(key, PageRequest.of(page, size));
        Result result = new Result(true, customerGroupPage.getTotalPages(), cgRepo.count(), "", customerGroupPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> save(CustomerGroup customerGroup){
        Result result;
        if(customerGroup.getId() == null || customerGroup.getId() == 0){
            result = new Result(true, 0, cgRepo.count(), Messages.saveSuccessMessage, cgRepo.save(customerGroup));
        }else{
            result = new Result(true, 0, cgRepo.count(), Messages.updateSuccessMessage, cgRepo.saveAndFlush(customerGroup));
        }
        return result.resultMap;
    }

}

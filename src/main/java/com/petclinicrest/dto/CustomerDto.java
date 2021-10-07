package com.petclinicrest.dto;

import com.petclinicrest.elasticrepositories.ECustomerRepository;
import com.petclinicrest.entities.ActiveStatus;
import com.petclinicrest.entities.Customer;
import com.petclinicrest.repositories.CustomerRepository;
import com.petclinicrest.repositories.ReceiptRepository;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CustomerDto {

    final CustomerRepository cRepo;
    final ReceiptRepository rRepo;
    final ECustomerRepository ecRepo;

    public CustomerDto(CustomerRepository cRepo, ECustomerRepository ecRepo, ReceiptRepository rRepo) {
        this.cRepo = cRepo;
        this.ecRepo = ecRepo;
        this.rRepo = rRepo;
    }

    public Map<String, Object> find(Integer id){
        Optional<Customer> customer = cRepo.findById(id);
        Result result;
        if(customer.isPresent()){
            result = new Result(true, 0, cRepo.count(), "", customer);
        }else{
            result = new Result(false, 0, cRepo.count(), Messages.findCustomerFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> find(String no){
        Customer customer = cRepo.findByNo(no);
        Result result;
        if(customer != null){
            result = new Result(true, 0, cRepo.count(), "", customer);
        }else{
            result = new Result(false, 0, cRepo.count(), Messages.findCustomerFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, cRepo.count(), "", cRepo.findAll());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<Customer> customerPage = cRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, customerPage.getTotalPages(), cRepo.count(), "", customerPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer sid){
        Result result = new Result(true, 0, cRepo.count(), "", cRepo.findByStatus_Id(sid));
        return result.resultMap;
    }

    public Map<String, Object> list(Integer sid, Integer page, Integer size){
        Page<Customer> customerPage = cRepo.findByStatus_Id(sid, PageRequest.of(page, size));
        Result result = new Result(true, customerPage.getTotalPages(), cRepo.count(), "", customerPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, cRepo.count(), "", cRepo.searchByKey(key));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<Customer> customerPage = cRepo.searchByKey(key, PageRequest.of(page, size));
        Result result = new Result(true, customerPage.getTotalPages(), cRepo.count(), "", customerPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(Integer sid, String key){
        Result result = new Result(true, 0, cRepo.count(), "", cRepo.searchByKeyAndStatusId(key, sid));
        return result.resultMap;
    }

    public Map<String, Object> search(Integer sid, String key, Integer page, Integer size){
        Page<Customer> customerPage = cRepo.searchByKeyAndStatusId(key, sid, PageRequest.of(page, size));
        Result result = new Result(true, customerPage.getTotalPages(), cRepo.count(), "", customerPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> elasticSearch(String key){
        Result result = new Result(true, 0, ecRepo.count(), "", ecRepo.search(key));
        return result.resultMap;
    }

    public Map<String, Object> elasticSearch(String key, Integer page, Integer size){
        Page<com.petclinicrest.elasticmodels.Customer> eCustomerPage = ecRepo.search(key, PageRequest.of(page, size));
        Result result = new Result(true, eCustomerPage.getTotalPages(), ecRepo.count(), "", eCustomerPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> save(Customer customer){
        Result result;
        if(customer.getId() == null || customer.getId() == 0){
            customer.setStatus(new ActiveStatus(1, ""));
            Customer savedCustomer = cRepo.save(customer);
            com.petclinicrest.elasticmodels.Customer eCustomer = new com.petclinicrest.elasticmodels.Customer();
            eCustomer.setCid(savedCustomer.getId());
            eCustomer.setCno(savedCustomer.getNo());
            eCustomer.setCname(savedCustomer.getName());
            eCustomer.setCsurname(savedCustomer.getSurname());
            ecRepo.save(eCustomer);
            result = new Result(true, 0, cRepo.count(), Messages.saveSuccessMessage, savedCustomer);
        }else{
            result = new Result(true, 0, cRepo.count(), Messages.updateSuccessMessage, cRepo.saveAndFlush(customer));
        }
        return result.resultMap;
    }

    public Map<String, Object> delete(Customer customer){
        customer.setStatus(new ActiveStatus(2, ""));
        Result result = new Result(true, 0, cRepo.count(), Messages.deleteSuccessMessage, cRepo.save(customer));
        return result.resultMap;
    }

}

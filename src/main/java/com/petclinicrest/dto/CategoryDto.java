package com.petclinicrest.dto;

import com.petclinicrest.entities.Category;
import com.petclinicrest.entities.Customer;
import com.petclinicrest.entities.Product;
import com.petclinicrest.repositories.CategoryRepository;
import com.petclinicrest.repositories.ProductRepository;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CategoryDto {

    final CategoryRepository cRepo;
    final ProductRepository pRepo;

    public CategoryDto(CategoryRepository cRepo, ProductRepository pRepo) {
        this.cRepo = cRepo;
        this.pRepo = pRepo;
    }


    public Map<String, Object> find(Integer id){
        Optional<Category> category = cRepo.findById(id);
        Result result;
        if(category.isPresent()){
            result = new Result(true, 0, cRepo.count(), "", category);
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
        Page<Category> categoryPage = cRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, categoryPage.getTotalPages(), cRepo.count(), "", categoryPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listProducts(Integer id){
        Result result = new Result(true, 0, pRepo.count(), "", pRepo.findByCategory_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listProducts(Integer id, Integer page, Integer size){
        Page<Product> productPage = pRepo.findByCategory_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, productPage.getTotalPages(), pRepo.count(), "", productPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, cRepo.count(), "", cRepo.searchByKey(key));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<Category> categoryPage = cRepo.searchByKey(key, PageRequest.of(page, size));
        Result result = new Result(true, categoryPage.getTotalPages(), cRepo.count(), "", categoryPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> save(Category category){
        Result result;
        if(category.getId() == null || category.getId() == 0){
            result = new Result(true, 0, cRepo.count(), Messages.saveSuccessMessage, cRepo.save(category));
        }else{
            result = new Result(true, 0, cRepo.count(), Messages.updateSuccessMessage, cRepo.saveAndFlush(category));
        }
        return result.resultMap;
    }

}

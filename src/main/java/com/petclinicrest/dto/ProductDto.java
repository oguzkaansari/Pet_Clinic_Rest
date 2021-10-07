package com.petclinicrest.dto;

import com.petclinicrest.entities.Product;
import com.petclinicrest.repositories.ProductRepository;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ProductDto {

    final ProductRepository pRepo;

    public ProductDto(ProductRepository pRepo) {
        this.pRepo = pRepo;
    }

    public Map<String, Object> find(Integer id){
        Optional<Product> product = pRepo.findById(id);
        Result result;
        if(product.isPresent()){
            result = new Result(true, 0, pRepo.count(), "", product);
        }else{
            result = new Result(false, 0, pRepo.count(), Messages.findProductFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> findByCode(String code){
        Product product = pRepo.findByCode(code);
        Result result;
        if(product != null){
            result = new Result(true, 0, pRepo.count(), "", product);
        }else{
            result = new Result(false, 0, pRepo.count(), Messages.findProductFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> findByBarcode(String barcode){
        Product product = pRepo.findByBarcode(barcode);
        Result result;
        if(product != null){
            result = new Result(true, 0, pRepo.count(), "", product);
        }else{
            result = new Result(false, 0, pRepo.count(), Messages.findProductFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, pRepo.count(), "", pRepo.findAll());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<Product> productPage = pRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, productPage.getTotalPages(), pRepo.count(), "", productPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listBySupplierId(Integer id){
        Result result = new Result(true, 0, pRepo.count(), "", pRepo.findBySupplier_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listBySupplierId(Integer id, Integer page, Integer size){
        Page<Product> productPage = pRepo.findBySupplier_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, productPage.getTotalPages(), pRepo.count(), "", productPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByUnitId(Integer id){
        Result result = new Result(true, 0, pRepo.count(), "", pRepo.findByUnit_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByUnitId(Integer id, Integer page, Integer size){
        Page<Product> productPage = pRepo.findByUnit_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, productPage.getTotalPages(), pRepo.count(), "", productPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByTypeId(Integer id){
        Result result = new Result(true, 0, pRepo.count(), "", pRepo.findByType_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByTypeId(Integer id, Integer page, Integer size){
        Page<Product> productPage = pRepo.findByType_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, productPage.getTotalPages(), pRepo.count(), "", productPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, pRepo.count(), "", pRepo.searchByKey(key));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<Product> productPage = pRepo.searchByKey(key, PageRequest.of(page, size));
        Result result = new Result(true, productPage.getTotalPages(), pRepo.count(), "", productPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithSupplier(Integer sid, String key){
        Result result = new Result(true, 0, pRepo.count(), "", pRepo.searchByKeyAndSupplierId(key, sid));
        return result.resultMap;
    }

    public Map<String, Object> searchWithSupplier(Integer sid, String key, Integer page, Integer size){
        Page<Product> productPage = pRepo.searchByKeyAndSupplierId(key, sid, PageRequest.of(page, size));
        Result result = new Result(true, productPage.getTotalPages(), pRepo.count(), "", productPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithUnit(Integer sid, String key){
        Result result = new Result(true, 0, pRepo.count(), "", pRepo.searchByKeyAndUnitId(key, sid));
        return result.resultMap;
    }

    public Map<String, Object> searchWithUnit(Integer sid, String key, Integer page, Integer size){
        Page<Product> productPage = pRepo.searchByKeyAndUnitId(key, sid, PageRequest.of(page, size));
        Result result = new Result(true, productPage.getTotalPages(), pRepo.count(), "", productPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithType(Integer sid, String key){
        Result result = new Result(true, 0, pRepo.count(), "", pRepo.searchByKeyAndTypeId(key, sid));
        return result.resultMap;
    }

    public Map<String, Object> searchWithType(Integer sid, String key, Integer page, Integer size){
        Page<Product> productPage = pRepo.searchByKeyAndTypeId(key, sid, PageRequest.of(page, size));
        Result result = new Result(true, productPage.getTotalPages(), pRepo.count(), "", productPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> save(Product product){
        Result result;
        if(product.getId() == null || product.getId() == 0){
            result = new Result(true, 0, pRepo.count(), Messages.saveSuccessMessage, pRepo.save(product));
        }else{
            result = new Result(true, 0, pRepo.count(), Messages.updateSuccessMessage, pRepo.saveAndFlush(product));
        }
        return result.resultMap;
    }

}

package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.ProductDto;
import com.petclinicrest.entities.Product;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    final ProductDto pDto;

    public ProductController(ProductDto pDto) {
        this.pDto = pDto;
    }

    @GetMapping("/find/id={id}")
    public Map<String, Object> find(@PathVariable Integer id) {
        return pDto.find(id);
    }

    @GetMapping("/find/code={code}")
    public Map<String, Object> findByCode(@PathVariable String code) {
        return pDto.findByCode(code);
    }

    @GetMapping("/find/barcode={barcode}")
    public Map<String, Object> findByBarcode(@PathVariable String barcode) {
        return pDto.findByBarcode(barcode);
    }

    @GetMapping("/list/all")
    public Map<String, Object> list(){
        return pDto.list();
    }

    @GetMapping("/list/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer page, @PathVariable Integer size){
        return pDto.list(page, size);
    }

    @GetMapping("/list/supplier_id={id}")
    public Map<String, Object> listBySupplierId(@PathVariable Integer id){
        return pDto.listBySupplierId(id);
    }

    @GetMapping("/list/supplier_id={id}/page={page}/size={size}")
    public Map<String, Object> listBySupplierId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return pDto.listBySupplierId(id, page, size);
    }

    @GetMapping("/list/unit_id={id}")
    public Map<String, Object> listByUnitId(@PathVariable Integer id){
        return pDto.listByUnitId(id);
    }

    @GetMapping("/list/unit_id={id}/page={page}/size={size}")
    public Map<String, Object> listByUnitId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return pDto.listByUnitId(id, page, size);
    }

    @GetMapping("/list/type_id={id}")
    public Map<String, Object> listByTypeId(@PathVariable Integer id){
        return pDto.listByTypeId(id);
    }

    @GetMapping("/list/type_id={id}/page={page}/size={size}")
    public Map<String, Object> listByTypeId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return pDto.listByTypeId(id, page, size);
    }

    @GetMapping("/search/key={key}")
    public Map<String, Object> search(@PathVariable String key){
        return pDto.search(key);
    }

    @GetMapping("/search/key={key}/page={page}/size={size}")
    public Map<String, Object> search(@PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return pDto.search(key, page, size);
    }

    @GetMapping("/search/supplier_id={id}/key={key}")
    public Map<String, Object> searchWithSupplier(@PathVariable Integer id, @PathVariable String key){
        return pDto.searchWithSupplier(id, key);
    }

    @GetMapping("/search/supplier_id={id}/key={key}/page={page}/size={size}")
    public Map<String, Object> searchWithSupplier(@PathVariable Integer id, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return pDto.searchWithSupplier(id, key, page, size);
    }

    @GetMapping("/search/unit_id={id}/key={key}")
    public Map<String, Object> searchWithUnit(@PathVariable Integer id, @PathVariable String key){
        return pDto.searchWithUnit(id, key);
    }

    @GetMapping("/search/unit_id={id}/key={key}/page={page}/size={size}")
    public Map<String, Object> searchWithUnit(@PathVariable Integer id, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return pDto.searchWithUnit(id, key, page, size);
    }


    @GetMapping("/search/type_id={id}/key={key}")
    public Map<String, Object> searchWithType(@PathVariable Integer id, @PathVariable String key){
        return pDto.searchWithType(id, key);
    }

    @GetMapping("/search/type_id={id}/key={key}/page={page}/size={size}")
    public Map<String, Object> searchWithType(@PathVariable Integer id, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return pDto.searchWithType(id, key, page, size);
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody @Valid Product product, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return pDto.save(product);
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody @Valid Product product, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return pDto.save(product);
    }

}

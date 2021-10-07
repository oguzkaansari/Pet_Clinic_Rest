package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.SupplierDto;
import com.petclinicrest.entities.Supplier;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    final SupplierDto sDto;

    public SupplierController(SupplierDto sDto) {
        this.sDto = sDto;
    }

    @GetMapping("/find/id={id}")
    public Map<String, Object> find(@PathVariable Integer id) {
        return sDto.find(id);
    }

    @GetMapping("/list/all")
    public Map<String, Object> list(){
        return sDto.list();
    }

    @GetMapping("/list/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer page, @PathVariable Integer size){
        return sDto.list(page, size);
    }

    @GetMapping("/search/key={key}")
    public Map<String, Object> search(@PathVariable String key){
        return sDto.search(key);
    }

    @GetMapping("/search/key={key}/page={page}/size={size}")
    public Map<String, Object> search(@PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return sDto.search(key, page, size);
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody @Valid Supplier supplier, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return sDto.save(supplier);
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody @Valid Supplier supplier, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return sDto.save(supplier);
    }

}

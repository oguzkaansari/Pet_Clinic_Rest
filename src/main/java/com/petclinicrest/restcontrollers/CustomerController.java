package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.CustomerDto;
import com.petclinicrest.entities.Customer;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    final CustomerDto cDto;

    public CustomerController(CustomerDto cDto) {
        this.cDto = cDto;
    }

    @GetMapping("/find/id={id}")
    public Map<String, Object> find(@PathVariable Integer id) {
        return cDto.find(id);
    }

    @GetMapping("/find/no={no}")
    public Map<String, Object> find(@PathVariable String no) {
        return cDto.find(no);
    }

    @GetMapping("/list/all")
    public Map<String, Object> list(){
        return cDto.list();
    }

    @GetMapping("/list/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer page, @PathVariable Integer size){
        return cDto.list(page, size);
    }

    @GetMapping("/list/status_id={sid}")
    public Map<String, Object> list(@PathVariable Integer sid){
        return cDto.list(sid);
    }

    @GetMapping("/list/status_id{sid}/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer sid, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.list(sid, page, size);
    }

    @GetMapping("/search/key={key}")
    public Map<String, Object> search(@PathVariable String key){
        return cDto.search(key);
    }

    @GetMapping("/search/key={key}/page={page}/size={size}")
    public Map<String, Object> search(@PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.search(key, page, size);
    }

    @GetMapping("/search/status_id{sid}/key={key}")
    public Map<String, Object> search(@PathVariable Integer sid, @PathVariable String key){
        return cDto.search(sid, key);
    }

    @GetMapping("/search/status_id={sid}/key={key}/page={page}/size={size}")
    public Map<String, Object> search(@PathVariable Integer sid, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.search(sid, key, page, size);
    }

    @GetMapping("/elastic_search/key={key}")
    public Map<String, Object> elasticSearch(@PathVariable String key){
        return cDto.elasticSearch(key);
    }

    @GetMapping("/elastic_search/key={key}/page={page}/size={size}")
    public Map<String, Object> elasticSearch(@PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.elasticSearch(key, page, size);
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody @Valid Customer customer, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return cDto.save(customer);
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody @Valid Customer customer, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return cDto.save(customer);
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody @Valid Customer customer, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return cDto.delete(customer);
    }

}

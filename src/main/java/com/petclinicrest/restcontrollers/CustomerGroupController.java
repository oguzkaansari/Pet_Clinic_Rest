package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.CustomerGroupDto;
import com.petclinicrest.entities.CustomerGroup;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/customer_group")
public class CustomerGroupController {

    final CustomerGroupDto cDto;

    public CustomerGroupController(CustomerGroupDto cDto) {
        this.cDto = cDto;
    }

    @GetMapping("/find/id={id}")
    public Map<String, Object> find(@PathVariable Integer id) {
        return cDto.find(id);
    }

    @GetMapping("/list/all")
    public Map<String, Object> list(){
        return cDto.list();
    }

    @GetMapping("/list/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer page, @PathVariable Integer size){
        return cDto.list(page, size);
    }

    @GetMapping("/customers/list/group_id={id}")
    public Map<String, Object> list(@PathVariable Integer id){
        return cDto.listCustomers(id);
    }

    @GetMapping("/customers/list/group_id={id}/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listCustomers(id, page, size);
    }


    @GetMapping("/search/key={key}")
    public Map<String, Object> search(@PathVariable String key){
        return cDto.search(key);
    }

    @GetMapping("/search/key={key}/page={page}/size={size}")
    public Map<String, Object> search(@PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.search(key, page, size);
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody @Valid CustomerGroup customerGroup, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return cDto.save(customerGroup);
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody @Valid CustomerGroup customerGroup, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return cDto.save(customerGroup);
    }

}

package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.CategoryDto;
import com.petclinicrest.entities.Category;
import com.petclinicrest.entities.CustomerGroup;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    final CategoryDto cDto;

    public CategoryController(CategoryDto cDto) {
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

    @GetMapping("/products/list/category_id={id}")
    public Map<String, Object> list(@PathVariable Integer id){
        return cDto.listProducts(id);
    }

    @GetMapping("/products/list/category_id={id}/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listProducts(id, page, size);
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
    public Map<String, Object> add(@RequestBody @Valid Category category, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return cDto.save(category);
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody @Valid Category category, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return cDto.save(category);
    }

}

package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.TestDto;
import com.petclinicrest.entities.Supplier;
import com.petclinicrest.entities.Test;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    final TestDto tDto;

    public TestController(TestDto tDto) {
        this.tDto = tDto;
    }

    @GetMapping("/find/id={id}")
    public Map<String, Object> find(@PathVariable Integer id) {
        return tDto.find(id);
    }

    @GetMapping("/find/no={no}")
    public Map<String, Object> find(@PathVariable String no) {
        return tDto.find(no);
    }

    @GetMapping("/list/all")
    public Map<String, Object> list(){
        return tDto.list();
    }

    @GetMapping("/list/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer page, @PathVariable Integer size){
        return tDto.list(page, size);
    }

    @GetMapping("/list/status_id={id}")
    public Map<String, Object> listByStatusId(@PathVariable Integer id){
        return tDto.listByStatusId(id);
    }

    @GetMapping("/list/status_id={id}/page={page}/size={size}")
    public Map<String, Object> listByStatusId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return tDto.listByStatusId(id, page, size);
    }

    @GetMapping("/list/pet_id={id}")
    public Map<String, Object> listByPetId(@PathVariable Integer id){
        return tDto.listByPetId(id);
    }

    @GetMapping("/list/pet_id={id}/page={page}/size={size}")
    public Map<String, Object> listByPetId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return tDto.listByPetId(id, page, size);
    }

    @GetMapping("/list/pet_chipno={no}")
    public Map<String, Object> listByPetChipno(@PathVariable String no){
        return tDto.listByPetChipno(no);
    }

    @GetMapping("/list/pet_chipno={no}/page={page}/size={size}")
    public Map<String, Object> listByPetChipno(@PathVariable String no, @PathVariable Integer page, @PathVariable Integer size){
        return tDto.listByPetChipno(no, page, size);
    }

    @GetMapping("/list/pet_cardno={no}")
    public Map<String, Object> listByPetCardno(@PathVariable String no){
        return tDto.listByPetCardno(no);
    }

    @GetMapping("/list/pet_cardno={no}/page={page}/size={size}")
    public Map<String, Object> listByPetCardno(@PathVariable String no, @PathVariable Integer page, @PathVariable Integer size){
        return tDto.listByPetCardno(no, page, size);
    }

    @GetMapping("/search/key={key}")
    public Map<String, Object> search(@PathVariable String key){
        return tDto.search(key);
    }

    @GetMapping("/search/key={key}/page={page}/size={size}")
    public Map<String, Object> search(@PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return tDto.search(key, page, size);
    }

    @GetMapping("/search/status_id={id}/key={key}")
    public Map<String, Object> searchWithStatus(@PathVariable Integer id, @PathVariable String key){
        return tDto.searchWithStatus(id, key);
    }

    @GetMapping("/search/status_id={id}/key={key}/page={page}/size={size}")
    public Map<String, Object> searchWithStatus(@PathVariable Integer id, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return tDto.searchWithStatus(id, key, page, size);
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody @Valid Test test, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return tDto.save(test);
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody @Valid Test test, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return tDto.save(test);
    }

}

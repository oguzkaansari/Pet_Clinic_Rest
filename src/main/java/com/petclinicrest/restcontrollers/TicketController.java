package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.TicketDto;
import com.petclinicrest.entities.Ticket;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    final TicketDto tDto;

    public TicketController(TicketDto tDto) {
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

    @GetMapping("/list/supplier_id={id}")
    public Map<String, Object> listBySupplierId(@PathVariable Integer id){
        return tDto.listBySupplierId(id);
    }

    @GetMapping("/list/supplier_id={id}/page={page}/size={size}")
    public Map<String, Object> listBySupplierId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return tDto.listBySupplierId(id, page, size);
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

    @GetMapping("/search/supplier_id{id}/key={key}")
    public Map<String, Object> searchWithSupplier(@PathVariable Integer id, @PathVariable String key){
        return tDto.searchWithSupplier(id, key);
    }

    @GetMapping("/search/supplier_id={id}/key={key}/page={page}/size={size}")
    public Map<String, Object> searchWithSupplier(@PathVariable Integer id, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return tDto.searchWithSupplier(id, key, page, size);
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody @Valid Ticket ticket, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return tDto.save(ticket);
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody @Valid Ticket ticket, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return tDto.save(ticket);
    }

    @GetMapping("/pay/id={id}")
    public Map<String, Object> pay(@PathVariable Integer id){
        return tDto.pay(id);
    }

}

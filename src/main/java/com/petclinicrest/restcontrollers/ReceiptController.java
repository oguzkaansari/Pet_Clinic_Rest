package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.ReceiptDto;
import com.petclinicrest.entities.Receipt;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    final ReceiptDto rDto;

    public ReceiptController(ReceiptDto rDto) {
        this.rDto = rDto;
    }

    @GetMapping("/find/id={id}")
    public Map<String, Object> find(@PathVariable Integer id) {
        return rDto.find(id);
    }

    @GetMapping("/find/no={no}")
    public Map<String, Object> find(@PathVariable String no) {
        return rDto.find(no);
    }

    @GetMapping("/list/all")
    public Map<String, Object> list(){
        return rDto.list();
    }

    @GetMapping("/list/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer page, @PathVariable Integer size){
        return rDto.list(page, size);
    }

    @GetMapping("/list/customer_id={id}")
    public Map<String, Object> listByCustomerId(@PathVariable Integer id){
        return rDto.listByCustomerId(id);
    }

    @GetMapping("/list/customer_id={id}/page={page}/size={size}")
    public Map<String, Object> listByCustomerId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return rDto.listByCustomerId(id, page, size);
    }

    @GetMapping("/list/status_id={id}")
    public Map<String, Object> listByStatusId(@PathVariable Integer id){
        return rDto.listByStatusId(id);
    }

    @GetMapping("/list/status_id={id}/page={page}/size={size}")
    public Map<String, Object> listByStatusId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return rDto.listByStatusId(id, page, size);
    }

    @GetMapping("/list/paytype_id={id}")
    public Map<String, Object> listByPaytypeId(@PathVariable Integer id){
        return rDto.listByPaytypeId(id);
    }

    @GetMapping("/list/paytype_id={id}/page={page}/size={size}")
    public Map<String, Object> listByPaytypeId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return rDto.listByPaytypeId(id, page, size);
    }

    @GetMapping("/list/customer_id={cid}/status_id={sid}")
    public Map<String, Object> listByCustomerIdAndStatusId(@PathVariable Integer cid, @PathVariable Integer sid){
        return rDto.listByCustomerIdAndStatusId(cid, sid);
    }

    @GetMapping("/list/customer_id={cid}/status_id={sid}/page={page}/size={size}")
    public Map<String, Object> listByCustomerIdAndStatusId(@PathVariable Integer cid, @PathVariable Integer sid, @PathVariable Integer page, @PathVariable Integer size){
        return rDto.listByCustomerIdAndStatusId(cid, sid, page, size);
    }

    @GetMapping("/search/key={key}")
    public Map<String, Object> search(@PathVariable String key){
        return rDto.search(key);
    }

    @GetMapping("/search/key={key}/page={page}/size={size}")
    public Map<String, Object> search(@PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return rDto.search(key, page, size);
    }

    @GetMapping("/search/customer_id={id}/key={key}")
    public Map<String, Object> searchWithCustomer(@PathVariable Integer id, @PathVariable String key){
        return rDto.searchWithStatus(id, key);
    }

    @GetMapping("/search/customer_id={id}/key={key}/page={page}/size={size}")
    public Map<String, Object> searchWithCustomer(@PathVariable Integer id, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return rDto.searchWithCustomer(id, key, page, size);
    }

    @GetMapping("/search/status_id={id}/key={key}")
    public Map<String, Object> searchWithStatus(@PathVariable Integer id, @PathVariable String key){
        return rDto.searchWithStatus(id, key);
    }

    @GetMapping("/search/status_id={id}/key={key}/page={page}/size={size}")
    public Map<String, Object> searchWithStatus(@PathVariable Integer id, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return rDto.searchWithStatus(id, key, page, size);
    }

    @GetMapping("/search/paytype_id={id}/key={key}")
    public Map<String, Object> searchWithPaytype(@PathVariable Integer id, @PathVariable String key){
        return rDto.searchWithPaytype(id, key);
    }

    @GetMapping("/search/paytype_id={id}/key={key}/page={page}/size={size}")
    public Map<String, Object> searchWithPaytype(@PathVariable Integer id, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return rDto.searchWithPaytype(id, key, page, size);
    }

    @GetMapping("/search/customer_id={cid}/status_id={sid}/key={key}")
    public Map<String, Object> searchWithCustomerAndStatus(@PathVariable Integer cid, @PathVariable Integer sid, @PathVariable String key){
        return rDto.searchWithCustomerAndStatus(cid, sid, key);
    }

    @GetMapping("/search/customer_id={cid}/status_id={sid}/key={key}/page={page}/size={size}")
    public Map<String, Object> searchWithCustomerAndStatus(@PathVariable Integer cid, @PathVariable Integer sid, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return rDto.searchWithCustomerAndStatus(cid, sid, key, page, size);
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody @Valid Receipt receipt, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return rDto.add(receipt);
    }

    @GetMapping("/pay/id={id}")
    public Map<String, Object> pay(@PathVariable Integer id){
        return rDto.pay(id);
    }

}

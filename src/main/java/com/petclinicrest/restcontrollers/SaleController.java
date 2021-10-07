package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.SaleDto;
import com.petclinicrest.entities.Sale;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/sale")
public class SaleController {

    final SaleDto sDto;

    public SaleController(SaleDto sDto) {
        this.sDto = sDto;
    }

    @GetMapping("/list/all")
    public Map<String, Object> list(){
        return sDto.list();
    }

    @GetMapping("/list/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer page, @PathVariable Integer size){
        return sDto.list(page, size);
    }

    @GetMapping("/list/receipt_id={rid}")
    public Map<String, Object> listByReceiptId(@PathVariable Integer rid){
        return sDto.listByReceiptId(rid);
    }

    @GetMapping("/list/receipt_id={rid}/page={page}/size={size}")
    public Map<String, Object> listByReceiptId(@PathVariable Integer rid, @PathVariable Integer page, @PathVariable Integer size){
        return sDto.listByReceiptId(rid, page, size);
    }

    @GetMapping("/list/receipt_no={no}")
    public Map<String, Object> listByReceiptNo(@PathVariable String no){
        return sDto.listByReceiptNo(no);
    }

    @GetMapping("/list/receipt_no={no}/page={page}/size={size}")
    public Map<String, Object> listByReceiptNo(@PathVariable String no, @PathVariable Integer page, @PathVariable Integer size){
        return sDto.listByReceiptNo(no, page, size);
    }

    @GetMapping("/list/product_id={pid}")
    public Map<String, Object> listByProductId(@PathVariable Integer pid){
        return sDto.listByProductId(pid);
    }

    @GetMapping("/list/product_id={pid}/page={page}/size={size}")
    public Map<String, Object> listByProductId(@PathVariable Integer pid, @PathVariable Integer page, @PathVariable Integer size){
        return sDto.listByProductId(pid, page, size);
    }

    @GetMapping("/list/product_code={code}")
    public Map<String, Object> listByProductCode(@PathVariable String code){
        return sDto.listByProductCode(code);
    }

    @GetMapping("/list/product_code={code}/page={page}/size={size}")
    public Map<String, Object> listByProductCode(@PathVariable String code, @PathVariable Integer page, @PathVariable Integer size){
        return sDto.listByProductCode(code, page, size);
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
    public Map<String, Object> add(@RequestBody @Valid Sale sale, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return sDto.add(sale);
    }

}

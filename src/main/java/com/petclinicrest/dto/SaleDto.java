package com.petclinicrest.dto;

import com.petclinicrest.entities.PayStatus;
import com.petclinicrest.entities.Receipt;
import com.petclinicrest.entities.Sale;
import com.petclinicrest.repositories.ReceiptRepository;
import com.petclinicrest.repositories.SaleRepository;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SaleDto {

    final SaleRepository sRepo;
    final ReceiptRepository rRepo;

    public SaleDto(SaleRepository sRepo, ReceiptRepository rRepo) {
        this.sRepo = sRepo;
        this.rRepo = rRepo;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, sRepo.count(), "", sRepo.findAll());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<Sale> salePage = sRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, salePage.getTotalPages(), sRepo.count(), "", salePage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByReceiptId(Integer id){
        Result result = new Result(true, 0, sRepo.count(), "", sRepo.findByReceipt_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByReceiptId(Integer id, Integer page, Integer size){
        Page<Sale> salePage = sRepo.findByReceipt_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, salePage.getTotalPages(), sRepo.count(), "", salePage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByReceiptNo(String no){
        Result result = new Result(true, 0, sRepo.count(), "", sRepo.findByReceipt_No(no));
        return result.resultMap;
    }

    public Map<String, Object> listByReceiptNo(String no, Integer page, Integer size){
        Page<Sale> salePage = sRepo.findByReceipt_No(no, PageRequest.of(page, size));
        Result result = new Result(true, salePage.getTotalPages(), sRepo.count(), "", salePage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByProductId(Integer id){
        Result result = new Result(true, 0, sRepo.count(), "", sRepo.findByProduct_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByProductId(Integer id, Integer page, Integer size){
        Page<Sale> salePage = sRepo.findByProduct_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, salePage.getTotalPages(), sRepo.count(), "", salePage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByProductCode(String code){
        Result result = new Result(true, 0, sRepo.count(), "", sRepo.findByProduct_Code(code));
        return result.resultMap;
    }

    public Map<String, Object> listByProductCode(String code, Integer page, Integer size){
        Page<Sale> salePage = sRepo.findByProduct_Code(code, PageRequest.of(page, size));
        Result result = new Result(true, salePage.getTotalPages(), sRepo.count(), "", salePage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, sRepo.count(), "", sRepo.searchByKey(key));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<Sale> ticketPage = sRepo.searchByKey(key, PageRequest.of(page, size));
        Result result = new Result(true, ticketPage.getTotalPages(), sRepo.count(), "", ticketPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> add(Sale sale){
        Sale savedSale =  sRepo.save(sale);
        Receipt receipt =  savedSale.getReceipt();
        receipt.setPrice(receipt.getPrice().add(savedSale.getProduct().getSell_price().multiply(savedSale.getAmount())));
        rRepo.save(receipt);
        Result result = new Result(true, 0, sRepo.count(), Messages.saveSuccessMessage, listByReceiptId(receipt.getId()));
        return result.resultMap;
    }

}

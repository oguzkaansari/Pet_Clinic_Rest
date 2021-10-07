package com.petclinicrest.dto;

import com.petclinicrest.entities.PayStatus;
import com.petclinicrest.entities.PayType;
import com.petclinicrest.entities.Receipt;
import com.petclinicrest.repositories.ReceiptRepository;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ReceiptDto {

    final ReceiptRepository rRepo;

    public ReceiptDto(ReceiptRepository rRepo) {
        this.rRepo = rRepo;
    }

    public Map<String, Object> find(Integer id){
        Optional<Receipt> receipt = rRepo.findById(id);
        Result result;
        if(receipt.isPresent()){
            result = new Result(true, 0, rRepo.count(), "", receipt);
        }else{
            result = new Result(false, 0, rRepo.count(), Messages.findReceiptFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> find(String no){
        Receipt receipt = rRepo.findByNo(no);
        Result result;
        if(receipt != null){
            result = new Result(true, 0, rRepo.count(), "", receipt);
        }else{
            result = new Result(false, 0, rRepo.count(), Messages.findReceiptFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, rRepo.count(), "", rRepo.findAll());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<Receipt> receiptPage = rRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, receiptPage.getTotalPages(), rRepo.count(), "", receiptPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByCustomerId(Integer id){
        Result result = new Result(true, 0, rRepo.count(), "", rRepo.findByCustomer_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByCustomerId(Integer id, Integer page, Integer size){
        Page<Receipt> receiptPage = rRepo.findByCustomer_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, receiptPage.getTotalPages(), rRepo.count(), "", receiptPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByStatusId(Integer id){
        Result result = new Result(true, 0, rRepo.count(), "", rRepo.findByStatus_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByStatusId(Integer id, Integer page, Integer size){
        Page<Receipt> receiptPage = rRepo.findByStatus_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, receiptPage.getTotalPages(), rRepo.count(), "", receiptPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByPaytypeId(Integer id){
        Result result = new Result(true, 0, rRepo.count(), "", rRepo.findByPaytype_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByPaytypeId(Integer id, Integer page, Integer size){
        Page<Receipt> receiptPage = rRepo.findByPaytype_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, receiptPage.getTotalPages(), rRepo.count(), "", receiptPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByCustomerIdAndStatusId(Integer cid, Integer sid){
        Result result = new Result(true, 0, rRepo.count(), "", rRepo.findByCustomer_IdAndStatus_Id(cid, sid));
        return result.resultMap;
    }

    public Map<String, Object> listByCustomerIdAndStatusId(Integer cid, Integer sid, Integer page, Integer size){
        Page<Receipt> receiptPage = rRepo.findByCustomer_IdAndStatus_Id(cid, sid, PageRequest.of(page, size));
        Result result = new Result(true, receiptPage.getTotalPages(), rRepo.count(), "", receiptPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, rRepo.count(), "", rRepo.searchByKey(key));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<Receipt> receiptPage = rRepo.searchByKey(key, PageRequest.of(page, size));
        Result result = new Result(true, receiptPage.getTotalPages(), rRepo.count(), "", receiptPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithCustomer(Integer id, String key){
        Result result = new Result(true, 0, rRepo.count(), "", rRepo.searchByKeyAndCustomerId(key, id));
        return result.resultMap;
    }

    public Map<String, Object> searchWithCustomer(Integer id, String key, Integer page, Integer size){
        Page<Receipt> receiptPage = rRepo.searchByKeyAndCustomerId(key, id, PageRequest.of(page, size));
        Result result = new Result(true, receiptPage.getTotalPages(), rRepo.count(), "", receiptPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithStatus(Integer id, String key){
        Result result = new Result(true, 0, rRepo.count(), "", rRepo.searchByKeyAndStatusId(key, id));
        return result.resultMap;
    }

    public Map<String, Object> searchWithStatus(Integer id, String key, Integer page, Integer size){
        Page<Receipt> receiptPage = rRepo.searchByKeyAndStatusId(key, id, PageRequest.of(page, size));
        Result result = new Result(true, receiptPage.getTotalPages(), rRepo.count(), "", receiptPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithPaytype(Integer id, String key){
        Result result = new Result(true, 0, rRepo.count(), "", rRepo.searchByKeyAndPaytypeId(key, id));
        return result.resultMap;
    }

    public Map<String, Object> searchWithPaytype(Integer id, String key, Integer page, Integer size){
        Page<Receipt> receiptPage = rRepo.searchByKeyAndPaytypeId(key, id, PageRequest.of(page, size));
        Result result = new Result(true, receiptPage.getTotalPages(), rRepo.count(), "", receiptPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithCustomerAndStatus(Integer cid, Integer sid, String key){
        Result result = new Result(true, 0, rRepo.count(), "", rRepo.searchByKeyAndCustomerIdAndStatusId(key, cid, sid));
        return result.resultMap;
    }

    public Map<String, Object> searchWithCustomerAndStatus(Integer cid, Integer sid, String key, Integer page, Integer size){
        Page<Receipt> receiptPage = rRepo.searchByKeyAndCustomerIdAndStatusId(key, cid, sid, PageRequest.of(page, size));
        Result result = new Result(true, receiptPage.getTotalPages(), rRepo.count(), "", receiptPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> add(Receipt receipt){
        receipt.setPaytype(new PayType(6, ""));
        receipt.setStatus(new PayStatus(2, ""));
        Result result = new Result(true, 0, rRepo.count(), Messages.saveSuccessMessage, rRepo.save(receipt));
        return result.resultMap;
    }

    public Map<String, Object> pay(Integer id){
        Result result;
        Optional<Receipt> optReceipt = rRepo.findById(id);
        if(optReceipt.isPresent()){
            Receipt receipt = optReceipt.get();
            receipt.setStatus(new PayStatus(1, ""));
            result = new Result(true, 0, rRepo.count(), Messages.paySuccessMessage, rRepo.saveAndFlush(receipt));
        }else{
            result = new Result(false, 0, rRepo.count(), Messages.receiptNotFoundMessage, null);
        }
        return result.resultMap;
    }
}

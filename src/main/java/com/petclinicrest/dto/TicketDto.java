package com.petclinicrest.dto;

import com.petclinicrest.entities.PayStatus;
import com.petclinicrest.entities.Ticket;
import com.petclinicrest.repositories.TicketRepository;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class TicketDto {

    final TicketRepository tRepo;

    public TicketDto(TicketRepository tRepo) {
        this.tRepo = tRepo;
    }

    public Map<String, Object> find(Integer id){
        Optional<Ticket> ticket = tRepo.findById(id);
        Result result;
        if(ticket.isPresent()){
            result = new Result(true, 0, tRepo.count(), "", ticket);
        }else{
            result = new Result(false, 0, tRepo.count(), Messages.findTicketFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> find(String no){
        Ticket ticket = tRepo.findByNo(no);
        Result result;
        if(ticket != null){
            result = new Result(true, 0, tRepo.count(), "", ticket);
        }else{
            result = new Result(false, 0, tRepo.count(), Messages.findTicketFailMessage, null);
        }
        return result.resultMap;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.findAll());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<Ticket> ticketPage = tRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, ticketPage.getTotalPages(), tRepo.count(), "", ticketPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByStatusId(Integer sid){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.findByStatus_Id(sid));
        return result.resultMap;
    }

    public Map<String, Object> listByStatusId(Integer sid, Integer page, Integer size){
        Page<Ticket> ticketPage = tRepo.findByStatus_Id(sid, PageRequest.of(page, size));
        Result result = new Result(true, ticketPage.getTotalPages(), tRepo.count(), "", ticketPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listBySupplierId(Integer sid){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.findBySupplier_Id(sid));
        return result.resultMap;
    }

    public Map<String, Object> listBySupplierId(Integer sid, Integer page, Integer size){
        Page<Ticket> ticketPage = tRepo.findBySupplier_Id(sid, PageRequest.of(page, size));
        Result result = new Result(true, ticketPage.getTotalPages(), tRepo.count(), "", ticketPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.searchByKey(key));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<Ticket> ticketPage = tRepo.searchByKey(key, PageRequest.of(page, size));
        Result result = new Result(true, ticketPage.getTotalPages(), tRepo.count(), "", ticketPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithStatus(Integer sid, String key){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.searchByKeyAndStatusId(key, sid));
        return result.resultMap;
    }

    public Map<String, Object> searchWithStatus(Integer sid, String key, Integer page, Integer size){
        Page<Ticket> ticketPage = tRepo.searchByKeyAndStatusId(key, sid, PageRequest.of(page, size));
        Result result = new Result(true, ticketPage.getTotalPages(), tRepo.count(), "", ticketPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> searchWithSupplier(Integer sid, String key){
        Result result = new Result(true, 0, tRepo.count(), "", tRepo.searchByKeyAndSupplierId(key, sid));
        return result.resultMap;
    }

    public Map<String, Object> searchWithSupplier(Integer sid, String key, Integer page, Integer size){
        Page<Ticket> ticketPage = tRepo.searchByKeyAndSupplierId(key, sid, PageRequest.of(page, size));
        Result result = new Result(true, ticketPage.getTotalPages(), tRepo.count(), "", ticketPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> save(Ticket ticket){
        Result result;
        if(ticket.getId() == null || ticket.getId() == 0){
            ticket.setStatus(new PayStatus(2, ""));
            result = new Result(true, 0, tRepo.count(), Messages.saveSuccessMessage, tRepo.save(ticket));
        }else{
            result = new Result(true, 0, tRepo.count(), Messages.updateSuccessMessage, tRepo.saveAndFlush(ticket));
        }
        return result.resultMap;
    }

    public Map<String, Object> pay(Integer id){
        Result result;
        Optional<Ticket> optTicket = tRepo.findById(id);
        if(optTicket.isPresent()){
            Ticket ticket = optTicket.get();
            ticket.setStatus(new PayStatus(1, ""));
            result = new Result(true, 0, tRepo.count(), Messages.paySuccessMessage, tRepo.saveAndFlush(ticket));
        }else{
            result = new Result(false, 0, tRepo.count(), Messages.findTicketFailMessage, null);
        }
        return result.resultMap;
    }

}

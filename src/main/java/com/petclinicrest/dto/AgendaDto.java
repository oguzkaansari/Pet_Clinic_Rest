package com.petclinicrest.dto;

import com.petclinicrest.entities.Note;
import com.petclinicrest.entities.User;
import com.petclinicrest.repositories.NoteRepository;
import com.petclinicrest.services.UserService;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Map;

@Service
public class AgendaDto {

    final NoteRepository nRepo;
    final UserService uService;


    public AgendaDto(NoteRepository nRepo, UserService uService) {
        this.nRepo = nRepo;
        this.uService = uService;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, nRepo.count(), "", nRepo.findByUser_Id(uService.getUserId()));
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<Note> notePage = nRepo.findByUser_Id(uService.getUserId(), PageRequest.of(page, size));
        Result result = new Result(true, notePage.getTotalPages(), nRepo.count(), "", notePage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> list(Date date){
        Result result = new Result(true, 0, nRepo.count(), "", nRepo.findByUser_IdAndDate(uService.getUserId(), date));
        return result.resultMap;
    }

    public Map<String, Object> list(Date date, Integer page, Integer size){
        Page<Note> notePage = nRepo.findByUser_IdAndDate(uService.getUserId(), date, PageRequest.of(page, size));
        Result result = new Result(true, notePage.getTotalPages(), nRepo.count(), "", notePage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> search(String key){
        Result result = new Result(true, 0, nRepo.count(), "", nRepo.searchByKey(key, uService.getUserId()));
        return result.resultMap;
    }

    public Map<String, Object> search(String key, Integer page, Integer size){
        Page<Note> notePage = nRepo.searchByKey(key, uService.getUserId(), PageRequest.of(page, size));
        Result result = new Result(true, notePage.getTotalPages(), nRepo.count(), "", notePage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> save(Note note){
        Result result;
        if(note.getId() == null || note.getId() == 0){
            note.setUser(uService.getUser());
            result = new Result(true, 0, nRepo.count(), Messages.saveSuccessMessage, nRepo.save(note));
        }else{
            result = new Result(true, 0, nRepo.count(), Messages.updateSuccessMessage, nRepo.saveAndFlush(note));
        }
        return result.resultMap;
    }

    public Map<String, Object> delete(Note note){
        Result result = new Result(true, 0, nRepo.count(), Messages.deleteSuccessMessage, note);
        return result.resultMap;
    }

}
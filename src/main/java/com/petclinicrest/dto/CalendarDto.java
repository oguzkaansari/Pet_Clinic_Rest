package com.petclinicrest.dto;

import com.petclinicrest.entities.Meeting;
import com.petclinicrest.entities.Role;
import com.petclinicrest.entities.User;
import com.petclinicrest.repositories.MeetingRepository;
import com.petclinicrest.services.UserService;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

@Service
public class CalendarDto {

    final MeetingRepository mRepo;
    final UserService uService;

    public CalendarDto(MeetingRepository mRepo, UserService uService) {
        this.mRepo = mRepo;
        this.uService = uService;
    }

    public Map<String, Object> list(){
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findAll());
        return result.resultMap;
    }

    public Map<String, Object> list(Integer page, Integer size){
        Page<Meeting> meetingPage = mRepo.findAll(PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> list(Date date){
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDate(date));
        return result.resultMap;
    }

    public Map<String, Object> list(Date date, Integer page, Integer size){
        Page<Meeting> meetingPage = mRepo.findByDate(date, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByCustomerId(Integer id){
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByCustomer_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByCustomerId(Integer id, Integer page, Integer size){
        Page<Meeting> meetingPage = mRepo.findByCustomer_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByCustomerNo(String no){
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByCustomer_No(no));
        return result.resultMap;
    }

    public Map<String, Object> listByCustomerNo(String no, Integer page, Integer size){
        Page<Meeting> meetingPage = mRepo.findByCustomer_No(no, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByPetId(Integer id){
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByPet_Id(id));
        return result.resultMap;
    }

    public Map<String, Object> listByPetId(Integer id, Integer page, Integer size){
        Page<Meeting> meetingPage = mRepo.findByPet_Id(id, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByPetChipNo(String no){
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByPet_Chipno(no));
        return result.resultMap;
    }

    public Map<String, Object> listByPetChipNo(String no, Integer page, Integer size){
        Page<Meeting> meetingPage = mRepo.findByPet_Chipno(no, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByPetCardNo(String no){
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByPet_Cardno(no));
        return result.resultMap;
    }

    public Map<String, Object> listByPetCardNo(String no, Integer page, Integer size){
        Page<Meeting> meetingPage = mRepo.findByPet_Cardno(no, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listBeforeToday(){
        Date date = new java.sql.Date(new java.util.Date().getTime());
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDateLessThanEqualAndHourLessThan(date, LocalTime.now()));
        return result.resultMap;
    }

    public Map<String, Object> listBeforeToday(Integer page, Integer size){
        Date date = new java.sql.Date(new java.util.Date().getTime());
        Page<Meeting> meetingPage = mRepo.findByDateLessThanEqualAndHourLessThan(date, LocalTime.now(), PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listAfterToday(){
        Date date = new java.sql.Date(new java.util.Date().getTime());
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDateGreaterThanEqualAndHourGreaterThan(date, LocalTime.now()));
        return result.resultMap;
    }

    public Map<String, Object> listAfterToday(Integer page, Integer size){
        Date date = new java.sql.Date(new java.util.Date().getTime());
        Page<Meeting> meetingPage = mRepo.findByDateGreaterThanEqualAndHourGreaterThan(date, LocalTime.now(), PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorId(Integer uid){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDoctor_Id(uid));
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorId(Integer uid, Integer page, Integer size){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Page<Meeting> meetingPage = mRepo.findByDoctor_Id(uid, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndDate(Integer uid, Date date){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDoctor_IdAndDate(uid, date));
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndDate(Integer uid, Date date, Integer page, Integer size){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Page<Meeting> meetingPage = mRepo.findByDoctor_IdAndDate(uid, date, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndCustomerId(Integer uid, Integer cid){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDoctor_IdAndCustomer_Id(uid, cid));
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndCustomerId(Integer uid, Integer cid, Integer page, Integer size){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Page<Meeting> meetingPage = mRepo.findByDoctor_IdAndCustomer_Id(uid, cid, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndCustomerNo(Integer uid, String no){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDoctor_IdAndCustomer_No(uid, no));
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndCustomerNo(Integer uid, String no, Integer page, Integer size){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Page<Meeting> meetingPage = mRepo.findByDoctor_IdAndCustomer_No(uid, no, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndPetId(Integer uid, Integer pid){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDoctor_IdAndPet_Id(uid, pid));
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndPetId(Integer uid, Integer pid, Integer page, Integer size){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Page<Meeting> meetingPage = mRepo.findByDoctor_IdAndPet_Id(uid, pid, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndPetChipNo(Integer uid, String no){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDoctor_IdAndPet_Chipno(uid, no));
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndPetChipNo(Integer uid, String no, Integer page, Integer size){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Page<Meeting> meetingPage = mRepo.findByDoctor_IdAndPet_Chipno(uid, no, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndPetCardNo(Integer uid, String no){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDoctor_IdAndPet_Cardno(uid, no));
        return result.resultMap;
    }

    public Map<String, Object> listByDoctorIdAndPetCardNo(Integer uid, String no, Integer page, Integer size){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Page<Meeting> meetingPage = mRepo.findByDoctor_IdAndPet_Cardno(uid, no, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listBeforeTodayByDoctorId(Integer uid){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Date date = new java.sql.Date(new java.util.Date().getTime());
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDateLessThanEqualAndHourLessThanAndDoctor_Id(date, LocalTime.now(), uid));
        return result.resultMap;
    }

    public Map<String, Object> listBeforeTodayByDoctorId(Integer uid, Integer page, Integer size){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Date date = new java.sql.Date(new java.util.Date().getTime());
        Page<Meeting> meetingPage = mRepo.findByDateLessThanEqualAndHourLessThanAndDoctor_Id(date, LocalTime.now(), uid, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> listAfterTodayByDoctorId(Integer uid){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Date date = new java.sql.Date(new java.util.Date().getTime());
        Result result = new Result(true, 0, mRepo.count(), "", mRepo.findByDateGreaterThanEqualAndHourGreaterThanAndDoctor_Id(date, LocalTime.now(), uid));
        return result.resultMap;
    }

    public Map<String, Object> listAfterTodayByDoctorId(Integer uid, Integer page, Integer size){
        if(!uService.checkId(uid)){
            Result result = new Result(false, 0, 0, Messages.authFailMessage, new ArrayList<>());
            return result.resultMap;
        }
        Date date = new java.sql.Date(new java.util.Date().getTime());
        Page<Meeting> meetingPage = mRepo.findByDateGreaterThanEqualAndHourGreaterThanAndDoctor_Id(date, LocalTime.now(), uid, PageRequest.of(page, size));
        Result result = new Result(true, meetingPage.getTotalPages(), mRepo.count(), "", meetingPage.getContent());
        return result.resultMap;
    }

    public Map<String, Object> save(Meeting meeting){
        Result result;
        if(meeting.getId() == null || meeting.getId() == 0){
            meeting.setDoctor(uService.getUser());
            result = new Result(true, 0, mRepo.count(), Messages.saveSuccessMessage, mRepo.save(meeting));
        }else{
            result = new Result(true, 0, mRepo.count(), Messages.updateSuccessMessage, mRepo.saveAndFlush(meeting));
        }
        return result.resultMap;
    }



}

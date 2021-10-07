package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.CalendarDto;
import com.petclinicrest.entities.Meeting;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Map;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    final CalendarDto cDto;

    public CalendarController(CalendarDto cDto) {
        this.cDto = cDto;
    }

    @GetMapping("/admin_secretary/list/all")
    public Map<String, Object> list(){
        return cDto.list();
    }

    @GetMapping("/admin_secretary/list/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer page, @PathVariable Integer size){
        return cDto.list(page, size);
    }

    @GetMapping("/admin_secretary/list/date={date}")
    public Map<String, Object> list(@PathVariable Date date){
        return cDto.list(date);
    }

    @GetMapping("/admin_secretary/list/date={date}/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Date date, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.list(date, page, size);
    }

    @GetMapping("/admin_secretary/list/customer_id={cid}")
    public Map<String, Object> listByCustomerId(@PathVariable Integer cid){
        return cDto.listByCustomerId(cid);
    }

    @GetMapping("/admin_secretary/list/customer_id={cid}/page={page}/size={size}")
    public Map<String, Object> listByCustomerId(@PathVariable Integer cid, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByCustomerId(cid, page, size);
    }

    @GetMapping("/admin_secretary/list/customer_no={no}")
    public Map<String, Object> listByCustomerNo(@PathVariable String no){
        return cDto.listByCustomerNo(no);
    }

    @GetMapping("/admin_secretary/list/customer_no={no}/page={page}/size={size}")
    public Map<String, Object> listByCustomerNo(@PathVariable String no, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByCustomerNo(no, page, size);
    }

    @GetMapping("/admin_secretary/list/pet_id={pid}")
    public Map<String, Object> listByPetId(@PathVariable Integer pid){
        return cDto.listByPetId(pid);
    }

    @GetMapping("/admin_secretary/list/pet_id={pid}/page={page}/size={size}")
    public Map<String, Object> listByPetId(@PathVariable Integer pid, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByPetId(pid, page, size);
    }

    @GetMapping("/admin_secretary/list/pet_chipno={no}")
    public Map<String, Object> listByPetChipNo(@PathVariable String no){
        return cDto.listByPetChipNo(no);
    }

    @GetMapping("/admin_secretary/list/pet_chipno={no}/page={page}/size={size}")
    public Map<String, Object> listByPetChipNo(@PathVariable String no, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByPetChipNo(no, page, size);
    }

    @GetMapping("/admin_secretary/list/pet_cardno={no}")
    public Map<String, Object> listByPetCardNo(@PathVariable String no){
        return cDto.listByPetCardNo(no);
    }

    @GetMapping("/admin_secretary/list/pet_cardno={no}/page={page}/size={size}")
    public Map<String, Object> listByPetCardNo(@PathVariable String no, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByPetCardNo(no, page, size);
    }

    @GetMapping("/admin_secretary/list/before_today")
    public Map<String, Object> listBeforeToday(){
        return cDto.listBeforeToday();
    }

    @GetMapping("/admin_secretary/list/before_today/page={page}/size={size}")
    public Map<String, Object> listBeforeToday(@PathVariable Integer page, @PathVariable Integer size){
        return cDto.listBeforeToday(page, size);
    }

    @GetMapping("/admin_secretary/list/after_today")
    public Map<String, Object> listAfterToday(){
        return cDto.listAfterToday();
    }

    @GetMapping("/admin_secretary/list/after_today/page={page}/size={size}")
    public Map<String, Object> listAfterToday(@PathVariable Integer page, @PathVariable Integer size){
        return cDto.listAfterToday(page, size);
    }

    @GetMapping("/list/doctor_id={uid}")
    public Map<String, Object> listByDoctorId(@PathVariable Integer uid){
        return cDto.listByDoctorId(uid);
    }

    @GetMapping("/list/doctor_id={uid}/page={page}/size={size}")
    public Map<String, Object> listByDoctorId(@PathVariable Integer uid, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByDoctorId(uid, page, size);
    }

    @GetMapping("/list/doctor_id={uid}/date={date}")
    public Map<String, Object> listByDoctorIdAndDate(@PathVariable Integer uid, @PathVariable Date date){
        return cDto.listByDoctorIdAndDate(uid, date);
    }

    @GetMapping("/list/doctor_id={uid}/date={date}/page={page}/size={size}")
    public Map<String, Object> listByDoctorIdAndDate(@PathVariable Integer uid, @PathVariable Date date, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByDoctorIdAndDate(uid, date, page, size);
    }

    @GetMapping("/list/doctor_id={uid}/customer_id={cid}")
    public Map<String, Object> listByDoctorIdAndCustomerId(@PathVariable Integer uid, @PathVariable Integer cid){
        return cDto.listByDoctorIdAndCustomerId(uid, cid);
    }

    @GetMapping("/list/doctor_id={id}/customer_id={cid}/page={page}/size={size}")
    public Map<String, Object> listByDoctorIdAndCustomerId(@PathVariable Integer id, @PathVariable Integer cid, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByDoctorIdAndCustomerId(id, cid, page, size);
    }

    @GetMapping("/list/doctor_id={uid}/customer_no={no}")
    public Map<String, Object> listByDoctorIdAndCustomerNo(@PathVariable Integer uid, @PathVariable String no){
        return cDto.listByDoctorIdAndCustomerNo(uid, no);
    }

    @GetMapping("/list/doctor_id={id}/customer_id={no}/page={page}/size={size}")
    public Map<String, Object> listByDoctorIdAndCustomerNo(@PathVariable Integer id, @PathVariable String no, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByDoctorIdAndCustomerNo(id, no, page, size);
    }

    @GetMapping("/list/doctor_id={uid}/pet_id={pid}")
    public Map<String, Object> listByDoctorIdAndPetId(@PathVariable Integer uid, @PathVariable Integer pid){
        return cDto.listByDoctorIdAndPetId(uid, pid);
    }

    @GetMapping("/list/doctor_id={id}/pet_id={pid}/page={page}/size={size}")
    public Map<String, Object> listByDoctorIdAndPetId(@PathVariable Integer id, @PathVariable Integer pid, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByDoctorIdAndPetId(id, pid, page, size);
    }

    @GetMapping("/list/doctor_id={uid}/pet_chipno={no}")
    public Map<String, Object> listByDoctorIdAndPetChipNo(@PathVariable Integer uid, @PathVariable String no){
        return cDto.listByDoctorIdAndPetChipNo(uid, no);
    }

    @GetMapping("/list/doctor_id={id}/pet_chipno={no}/page={page}/size={size}")
    public Map<String, Object> listByDoctorIdAndPetChipNo(@PathVariable Integer id, @PathVariable String no, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByDoctorIdAndPetChipNo(id, no, page, size);
    }

    @GetMapping("/list/doctor_id={uid}/pet_cardno={no}")
    public Map<String, Object> listByDoctorIdAndPetCardNo(@PathVariable Integer uid, @PathVariable String no){
        return cDto.listByDoctorIdAndPetCardNo(uid, no);
    }

    @GetMapping("/list/doctor_id={id}/pet_cardno={no}/page={page}/size={size}")
    public Map<String, Object> listByDoctorIdAndPetCardNo(@PathVariable Integer id, @PathVariable String no, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listByDoctorIdAndPetCardNo(id, no, page, size);
    }

    @GetMapping("/list/doctor_id={id}/before_today")
    public Map<String, Object> listBeforeTodayByDoctorId(@PathVariable Integer id){
        return cDto.listBeforeTodayByDoctorId(id);
    }

    @GetMapping("/list/doctor_id={id}/before_today/page={page}/size={size}")
    public Map<String, Object> listBeforeTodayByDoctorId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listBeforeTodayByDoctorId(id, page, size);
    }

    @GetMapping("/list/doctor_id={id}/after_today")
    public Map<String, Object> listAfterTodayByDoctorId(@PathVariable Integer id){
        return cDto.listAfterTodayByDoctorId(id);
    }

    @GetMapping("/list/doctor_id={id}/after_today/page={page}/size={size}")
    public Map<String, Object> listAfterTodayByDoctorId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return cDto.listAfterTodayByDoctorId(id, page, size);
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody @Valid Meeting meeting, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return cDto.save(meeting);
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody @Valid Meeting meeting, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return cDto.save(meeting);
    }

}

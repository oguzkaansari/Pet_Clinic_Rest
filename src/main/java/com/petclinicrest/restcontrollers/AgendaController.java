package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.AgendaDto;
import com.petclinicrest.entities.Note;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Map;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    final AgendaDto aDto;

    public AgendaController(AgendaDto aDto) {
        this.aDto = aDto;
    }

    @GetMapping("/list/all")
    public Map<String, Object> list(){
        return aDto.list();
    }

    @GetMapping("/list/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer page, @PathVariable Integer size){
        return aDto.list(page, size);
    }

    @GetMapping("/list/date={date}")
    public Map<String, Object> search(@PathVariable Date date){
        return aDto.list(date);
    }

    @GetMapping("/list/date={date}/page={page}/size={size}")
    public Map<String, Object> search(@PathVariable Date date, @PathVariable Integer page, @PathVariable Integer size){
        return aDto.list(date, page, size);
    }

    @GetMapping("/search/key={key}")
    public Map<String, Object> search(@PathVariable String key){
        return aDto.search(key);
    }

    @GetMapping("/search/key={key}/page={page}/size={size}")
    public Map<String, Object> search(@PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return aDto.search(key, page, size);
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody @Valid Note note, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return aDto.save(note);
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody @Valid Note note, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return aDto.save(note);
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody @Valid Note note, BindingResult bResult){
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return aDto.delete(note);
    }

}

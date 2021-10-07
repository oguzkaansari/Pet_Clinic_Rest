package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.UserDto;
import com.petclinicrest.entities.User;
import com.petclinicrest.utils.Messages;
import com.petclinicrest.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    final UserDto uDto;

    public AdminController(UserDto uDto) {
        this.uDto = uDto;
    }

    @GetMapping("/current_user")
    public Map<String, Object> find() {
        return uDto.find();
    }

    @GetMapping("/find/id={id}")
    public Map<String, Object> find(@PathVariable Integer id) {
        return uDto.find(id);
    }

    @GetMapping("/find/email={email}")
    public Map<String, Object> find(@PathVariable String email) {
        return uDto.find(email);
    }

    @GetMapping("/list/all")
    public Map<String, Object> list(){
        return uDto.list();
    }

    @GetMapping("/list/page={page}/size={size}")
    public Map<String, Object> list(@PathVariable Integer page, @PathVariable Integer size){
        return uDto.list(page, size);
    }

    @GetMapping("/list/status_id={id}")
    public Map<String, Object> listByStatusId(@PathVariable Integer id){
        return uDto.listByStatusId(id);
    }

    @GetMapping("/list/status_id={id}/page={page}/size={size}")
    public Map<String, Object> listByStatusId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return uDto.listByStatusId(id, page, size);
    }

    @GetMapping("/list/role_id={id}")
    public Map<String, Object> listByRolesId(@PathVariable Integer id){
        return uDto.listByRolesId(id);
    }

    @GetMapping("/list/role_id={id}/page={page}/size={size}")
    public Map<String, Object> listByRolesId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size){
        return uDto.listByRolesId(id, page, size);
    }

    @GetMapping("/search/key={key}")
    public Map<String, Object> search(@PathVariable String key){
        return uDto.search(key);
    }

    @GetMapping("/search/key={key}/page={page}/size={size}")
    public Map<String, Object> search(@PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return uDto.search(key, page, size);
    }

    @GetMapping("/search/status_id={id}/key={key}")
    public Map<String, Object> searchWithStatus(@PathVariable Integer id, @PathVariable String key){
        return uDto.searchWithStatus(id, key);
    }

    @GetMapping("/search/status_id={id}/key={key}/page={page}/size={size}")
    public Map<String, Object> searchWithStatus(@PathVariable Integer id, @PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        return uDto.searchWithStatus(id, key, page, size);
    }

    @PostMapping("/user/add")
    public Map<String, Object> add(@RequestBody @Valid User user, BindingResult bResult) throws AuthenticationException {
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return uDto.save(user);
    }

    @PostMapping("/user/upload_image/user_id={id}")
    public Map<String, Object> uploadImage(@PathVariable Integer id, @RequestPart("image") MultipartFile file) {
        return uDto.uploadImage(id, file);
    }

    @PostMapping("/user/update")
    public Map<String, Object> update(@RequestBody @Valid User user, BindingResult bResult) throws AuthenticationException {
        if(bResult.hasErrors()){
            Result result = new Result(false, 0, 0, Messages.validErrorMessage, bResult.toString());
            return result.resultMap;
        }
        return uDto.save(user);
    }

}

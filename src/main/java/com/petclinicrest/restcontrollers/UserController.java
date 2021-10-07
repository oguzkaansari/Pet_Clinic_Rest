package com.petclinicrest.restcontrollers;

import com.petclinicrest.dto.UserDto;
import com.petclinicrest.entities.ReferenceHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserDto uDto;

    public UserController(UserDto uDto) {
        this.uDto = uDto;
    }

    @GetMapping("/current_user")
    public Map<String, Object> find() {
        return uDto.find();
    }

    @GetMapping("/change_password/request")
    public Map<String, Object> changePassword() {
        return uDto.changePassword();
    }

    @PostMapping("/change_password/confirm")
    public Map<String, Object> changePasswordConfirm(@RequestBody ReferenceHolder referenceHolder) {
        return uDto.changePasswordConfirm(referenceHolder);
    }
}

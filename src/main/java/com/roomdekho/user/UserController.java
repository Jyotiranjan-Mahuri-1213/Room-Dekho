package com.roomdekho.user;

import com.roomdekho.user.dto.UserResponse;
import com.roomdekho.user.dto.LoginRequest;
import com.roomdekho.user.dto.LoginResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {

        this.userService = userService;

    }


    @PostMapping("/register")
    public UserResponse registerUser(
            @RequestBody User user
    ){

        return userService.registerUser(user);

    }


    @PostMapping("/login")
    public LoginResponse loginUser(
            @RequestBody LoginRequest request
    ){

        return userService.loginUser(request);

    }

}
package com.roomdekho.user;

import com.roomdekho.user.dto.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // REGISTER
    @PostMapping("/register")
    public UserResponse register(@RequestBody User users) {

        return userService.registerUser(users);
    }

    // login
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return userService.loginUser(request);
    }
    @PostMapping("/forgot-password")
    public String forgotPassword(
            @RequestBody ForgotPasswordRequest request) {

        userService.sendOtp(request.getEmail());

        return "OTP sent successfully";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestBody VerifyOtpRequest request) {

        userService.verifyOtp(
                request.getEmail(),
                request.getOtp()
        );

        return "OTP verified successfully";
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestBody ResetPasswordRequest request) {

        userService.resetPassword(
                request.getEmail(),
                request.getNewPassword(),
                request.getConfirmPassword()
        );

        return "Password reset successfully";
    }

    // GET CURRENT USER
    @GetMapping("/me")
    public UserProfileDTO getMyProfile() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDTO dto = new UserProfileDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());

        return dto;
    }
}
package com.roomdekho.user;

import com.roomdekho.user.dto.*;
import com.roomdekho.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final PasswordResetOtpRepository passwordResetOtpRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       PasswordResetOtpRepository passwordResetOtpRepository,
                       EmailService emailService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.passwordResetOtpRepository = passwordResetOtpRepository;
        this.emailService = emailService;
    }

    // REGISTER
    public UserResponse registerUser(User users) {

        if (userRepository.findByEmail(users.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        users.setRole("USER");
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        User saved = userRepository.save(users);

        return new UserResponse(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone(),
                saved.getRole()
        );
    }

    // LOGIN
    public LoginResponse loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole());

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );

        return new LoginResponse(token, response);
    }

    public void sendOtp(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not registered"));

        String otp = String.format("%06d",
                new java.util.Random().nextInt(1000000));

        PasswordResetOtp resetOtp = new PasswordResetOtp(
                email,
                otp,
                java.time.LocalDateTime.now().plusMinutes(5)
        );

        passwordResetOtpRepository.save(resetOtp);

        emailService.sendOtp(email, otp);
    }

    public void verifyOtp(String email, String otp) {

        PasswordResetOtp resetOtp =
                passwordResetOtpRepository
                        .findTopByEmailOrderByIdDesc(email)
                        .orElseThrow(() ->
                                new RuntimeException("OTP not found"));

        if (resetOtp.getExpiryTime()
                .isBefore(java.time.LocalDateTime.now())) {

            throw new RuntimeException("OTP expired");
        }

        if (!resetOtp.getOtp().equals(otp)) {

            throw new RuntimeException("Invalid OTP");
        }

        resetOtp.setVerified(true);

        passwordResetOtpRepository.save(resetOtp);
    }

    public void resetPassword(String email,
                              String newPassword,
                              String confirmPassword) {

        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("Passwords do not match");
        }

        PasswordResetOtp resetOtp =
                passwordResetOtpRepository
                        .findTopByEmailOrderByIdDesc(email)
                        .orElseThrow(() ->
                                new RuntimeException("OTP verification required"));

        if (!resetOtp.isVerified()) {
            throw new RuntimeException("Please verify OTP first");
        }

        if (resetOtp.getExpiryTime()
                .isBefore(java.time.LocalDateTime.now())) {

            throw new RuntimeException("OTP expired");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);

        // Delete used OTP
        passwordResetOtpRepository.delete(resetOtp);
    }
}
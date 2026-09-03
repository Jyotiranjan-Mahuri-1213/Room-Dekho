package com.roomdekho.user;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String email, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);

        message.setSubject("RoomDekho - Password Reset OTP");

        message.setText(
                "Your RoomDekho password reset OTP is: "
                        + otp
                        + "\n\n"
                        + "This OTP is valid for 5 minutes."
                        + "\n\n"
                        + "If you did not request a password reset, please ignore this email."
        );

        mailSender.send(message);
    }
}
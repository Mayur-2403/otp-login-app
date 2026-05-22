package com.otp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class OtpController {

    @Autowired
    private JavaMailSender mailSender;

    private String generatedOtp;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam("email") String email, Model model) {

        Random random = new Random();

        generatedOtp = String.valueOf(100000 + random.nextInt(900000));

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("mayur240307@gmail.com");
        
        message.setTo(email);
        
        message.setSubject("Your AnimeVerse OTP");
        
        message.setText("Your OTP is: " + generatedOtp);

        mailSender.send(message);

        model.addAttribute("email", email);

        return "otp";
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam("otp") String otp, Model model) {

        if (otp.equals(generatedOtp)) {

            model.addAttribute("message", "Login Successful!");

        } else {

            model.addAttribute("message", "Invalid OTP!");
        }

        return "result";
    }
}
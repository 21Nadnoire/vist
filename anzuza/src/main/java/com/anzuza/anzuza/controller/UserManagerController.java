package com.anzuza.anzuza.controller;

import com.anzuza.anzuza.model.User;
import com.anzuza.anzuza.service.UserManagerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class UserManagerController {

    @Autowired
    UserManagerService userManagerService;


    @GetMapping(value = "/main-page")
    public String loadMainPage() {
        return "main-page";
    }

    @GetMapping(value = "/sign-up")
    public String loadSignUpPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "sign-up";
    }

    @PostMapping(value = "/user/create")
    public String registerVisitor(@ModelAttribute("user") User user) {
        User savedUser = userManagerService.createUser(user);
        if (savedUser == null) {
            return "redirect:/error-page";
        }
        return "validate-phone";
    }

    @GetMapping(value = "/validate-phone")
    public String loadValidatePhoneNumberPage(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "validate-phone";
    }

    @PostMapping(value = "/user/send-otp")
    public String sendOtp(@ModelAttribute("user") User user) throws IOException {
        userManagerService.sendOtp(user.getPhoneNumber());
        return "validate-otp";
    }

    @PostMapping(value = "/user/auth")
    public String authenticateUser(@ModelAttribute("user") User user, HttpSession session){
        String userId = userManagerService.authenticateUser(user);
        if(userId == null){
            return "validate-phone";
        }
        System.out.println("User ID: "+userId);
        session.setAttribute("userId",userId);
        return "home-page";
    }

    @GetMapping(value = "/error-page")
    public String getErrorPage() {
        return "error-page";}
}

package com.itis.course.Controller;


import com.itis.course.ModelForm.UserForm;
import com.itis.course.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("userForm", new UserForm());
        return "account/register";
    }

    @PostMapping("/register")
    public String userResgistration(@Valid UserForm userForm, BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) throws ServletException {
        if(bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", userForm);
            return "account/register";
        }
        try {
            userService.register(userForm);
        } catch (Exception e) {
            bindingResult.rejectValue("email", "userData.email", "An account already exists for this email");
            model.addAttribute("registrationForm", userForm);
            return "account/register";
        }
        return "account/login";
    }



}

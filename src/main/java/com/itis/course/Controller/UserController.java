package com.itis.course.Controller;

import com.itis.course.Model.Users;
import com.itis.course.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/admin/users")
    public String allUsers(Model model){
        List<Users> allUsers = userService.retrieveAll();
        model.addAttribute("users", allUsers);
        return "/admin/allUsers";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteOneById(id);
        log.info("should delete user {}", id);
        return "redirect:/users";
    }

}

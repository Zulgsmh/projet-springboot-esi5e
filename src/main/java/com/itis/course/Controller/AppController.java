package com.itis.course.Controller;

import com.itis.course.Enum.TypePublication;
import com.itis.course.Model.Publication;
import com.itis.course.Model.Users;
import com.itis.course.Service.PublicationService;
import com.itis.course.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
public class AppController {

    @Autowired
    PublicationService publicationService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Publication> allMyPublications =  publicationService.getAll();
        Set<TypePublication> tagsSet = allMyPublications.stream()
                .map(Publication::getPublicationType).collect(Collectors.toSet())
                .stream().flatMap(Collection::stream).collect(Collectors.toSet());
        model.addAttribute("publications", allMyPublications);
        model.addAttribute("tags", tagsSet);
        return "home/home";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "/account/login";
    }

    @RequestMapping("/InsertPublication")
    public String newPub(Model model){
        return "/publication/InsertPublication";
    }

}

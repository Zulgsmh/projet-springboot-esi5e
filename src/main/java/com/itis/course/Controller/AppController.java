package com.itis.course.Controller;

import com.itis.course.Model.Publication;
import com.itis.course.Model.PublicationType;
import com.itis.course.Service.PublicationService;
import com.itis.course.Service.PublicationTypeService;
import com.itis.course.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Slf4j
@Controller
public class AppController {

    @Autowired
    final private PublicationService publicationService;

    @Autowired
    final private PublicationTypeService publicationTypeService;


    public AppController(PublicationService publicationService, PublicationTypeService publicationTypeService) {
        this.publicationService = publicationService;
        this.publicationTypeService = publicationTypeService;
    }

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Publication> allMyPublications =  publicationService.getAll();
        List<PublicationType> publicationTypeList = publicationTypeService.getAllPublicationType();
        model.addAttribute("publications", allMyPublications);
        model.addAttribute("tags", publicationTypeList);
        return "home/home";
    }

    @RequestMapping("/home/category/{id}")
    public String viewHomePage(@PathVariable long id , Model model){
        List<Publication> publicationsbyID =  publicationService.getAllByPublicationTypeId(id);
        List<PublicationType> publicationTypeList = publicationTypeService.getAllPublicationType();
        model.addAttribute("publications", publicationsbyID);
        model.addAttribute("tags", publicationTypeList);
        return "home/home";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "/account/login";
    }



}

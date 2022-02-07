package com.itis.course.Controller;

import com.itis.course.Enum.PublicationStatus;
import com.itis.course.Model.Langue;
import com.itis.course.Model.Publication;
import com.itis.course.Model.PublicationType;
import com.itis.course.Model.Users;
import com.itis.course.ModelForm.PublicationForm;
import com.itis.course.Service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @Autowired
    TraductionService traductionService;

    @Autowired
    LangueService langueService;


    @Autowired
    PublicationTypeService publicationTypeService;

    final static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";

    @RequestMapping("/InsertPublication")
    public String newPub(Model model){
        model.addAttribute("publicationLangues", langueService.listAll());
        model.addAttribute("publicationsType", publicationTypeService.getAllPublicationType());
        model.addAttribute("publicationForm", new PublicationForm());
        return "user/InsertPublication";
    }

    @PostMapping("/addPublication")
    public String addPublication(@Valid PublicationForm publicationForm, BindingResult result, Model model,
                                 @AuthenticationPrincipal Users user,
                                 @RequestParam("pubImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName ) throws IOException {
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            return "user/InsertPublication";
        }
        String imageUUID;
        if(!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        Optional<Langue> optionalLangue = Optional.ofNullable(langueService.getOne(Long.parseLong(publicationForm.getLangue())));
        Optional<PublicationType> pbType = publicationTypeService.getCategoryByName(publicationForm.getTag());
        Publication publication = new Publication(PublicationStatus.NonValide,publicationForm.getTexte(), user ,publicationForm.getSource(),
                publicationForm.getSens(), pbType.get(), optionalLangue.get(), user, imageUUID);
        publicationService.create(publication);
        log.info("publi created");
        return "redirect:/";
    }

    @RequestMapping("/admin/publications")
    public String allPublicationsForAdmin(Model model){
        List<Publication> publications = publicationService.getAll();
        model.addAttribute("publications", publications);
        return "/admin/allPublications";
    }

    @RequestMapping("/admin/deletePublication/{id}")
    public String deletePublication(@PathVariable("id") long id){
        publicationService.delete(id);
        log.info("should delete publication {}", id);
        return "redirect:/admin/publications";
    }

    @RequestMapping("/publication/details/{id}")
    public String getDetailsPublication(@PathVariable("id") long id, Model model) {
        model.addAttribute("publication",  publicationService.getOne(id));
        model.addAttribute("traductions", traductionService.getAllTraductionsByIdPublication(id));
        //fetch all traduction with this id
        return "/home/publicationDetails";
    }

    @RequestMapping("/admin/validatePublication/{id}")
    public String validatePublication(@PathVariable("id") long id, @AuthenticationPrincipal Users user){
        publicationService.validatePublication(id, user);
        return "redirect:/admin/publications";
    }

    @RequestMapping("/admin/unValidatePublication/{id}")
    public String unValidatePublication(@PathVariable("id") long id, @AuthenticationPrincipal Users user ){
        publicationService.unValidatePublication(id, user);
        return "redirect:/admin/publications";
    }




}

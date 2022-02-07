package com.itis.course.Controller;

import com.itis.course.Model.Langue;
import com.itis.course.Model.Publication;
import com.itis.course.Model.PublicationType;
import com.itis.course.Model.Traduction;
import com.itis.course.Service.LangueService;
import com.itis.course.Service.PublicationService;
import com.itis.course.Service.PublicationTypeService;
import com.itis.course.Service.TraductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    PublicationTypeService publicationTypeService;

    @Autowired
    LangueService langueService;

    @Autowired
    PublicationService publicationService;

    @Autowired
    TraductionService traductionService;

    //global admin
    @GetMapping("/admin")
    public String adminHome(){
        return "admin/adminHome";
    }

    //categories admin
    @GetMapping("/admin/categories")
    public String adminCategories(Model model){
        model.addAttribute("categories", publicationTypeService.getAllPublicationType());
        return "admin/allCategories";
    }

    @GetMapping("/admin/categories/add")
    public String addAdminCategory(Model model){
        model.addAttribute("category",  new PublicationType());
        return "admin/addAdminCategory";
    }

    @PostMapping("/admin/categories/add")
    public String postCategory(@ModelAttribute("category") PublicationType category){
        publicationTypeService.createPublicationType(category);
        return "redirect:/admin/categories";
    }

    //delete by id
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") long id){
        publicationTypeService.deleteOne(id);
        return "redirect:/admin/categories";
    }

    //update by id
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable("id") long id, Model model){
        Optional<PublicationType> publicationTypeOptional = publicationTypeService.getCategoryById(id);
        if(publicationTypeOptional.isPresent()) {
            model.addAttribute("category", publicationTypeOptional.get());
            return "admin/addAdminCategory";
        }
        return "404";
    }

    //-------------
    //Langues admin

    @GetMapping("/admin/langues")
    public String adminLangues(Model model){
        model.addAttribute("langues", langueService.listAll());
        return "admin/allLangues";
    }

    @GetMapping("/admin/langues/add")
    public String addAdminLangue(Model model){
        model.addAttribute("langueForm",  new Langue());
        return "admin/addAdminLangue";
    }

    @PostMapping("/admin/langues/add")
    public String postLangue(@ModelAttribute("langueForm") Langue langueForm){
        langueService.create(langueForm);
        return "redirect:/admin/langues";
    }

    //delete by id
    @GetMapping("/admin/langues/delete/{id}")
    public String deleteLangue(@PathVariable("id") long id){
        langueService.delete(id);
        return "redirect:/admin/langues";
    }

    //update by id
    @GetMapping("/admin/langues/update/{id}")
    public String updateLangue(@PathVariable("id") long id, Model model){
        Langue langue = langueService.getOne(id);
        if(langue != null) {
            model.addAttribute("langueForm", langue);
            return "admin/addAdminLangue";
        }
        return "404";
    }

    //-----------
    //Traductions

    @GetMapping("/specialist/traduction/add")
    public String createNewTraduction(Model model) {
        List<Publication> publicationList = publicationService.getAll();
        List<Langue> langueList = langueService.listAll();
        model.addAttribute("langues", langueList);
        model.addAttribute("publications", publicationList);
        model.addAttribute("traductionForm", new Traduction());
        return "specialist/addTraduction";
    }

    @PostMapping("/specialist/traduction/add")
    public String addTraduction(@ModelAttribute("traductionForm") Traduction traductionForm){
        traductionService.create(traductionForm);
        return "redirect:/";
    }



}

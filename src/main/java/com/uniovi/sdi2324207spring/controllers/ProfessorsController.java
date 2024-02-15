package com.uniovi.sdi2324207spring.controllers;

import com.uniovi.sdi2324207spring.entities.Mark;
import com.uniovi.sdi2324207spring.entities.Professor;
import com.uniovi.sdi2324207spring.services.MarksService;
import com.uniovi.sdi2324207spring.services.ProfessorsService;
import com.uniovi.sdi2324207spring.services.UsersService;
import com.uniovi.sdi2324207spring.validators.AddMarkFormValidator;
import com.uniovi.sdi2324207spring.validators.AddProfessorFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorsController {


    private ProfessorsService professorsService;
    private final AddProfessorFormValidator addProfessorFormValidator;

    public ProfessorsController(ProfessorsService professorsService, AddProfessorFormValidator addProfessorFormValidator) {
        this.professorsService = professorsService;
        this.addProfessorFormValidator = addProfessorFormValidator;
    }


    @RequestMapping("/professor/list")
    public String getList(Model model) {
        model.addAttribute("professorsList", professorsService.getProfessors());
        return "professor/list";
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(@Validated Professor professor, BindingResult result) {
        addProfessorFormValidator.validate(professor, result);
        if (result.hasErrors()) {
            return "/professor/add";
        }
        professorsService.addProfessor(professor);
        return "redirect:/professor/list";
    }


    @RequestMapping(value = "/professor/add")
    public String getProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/add";
    }

    @RequestMapping(value = "/professor/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("professor", professorsService.getProfessor(id));
        return "professor/edit";
    }

    @RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.POST)
    public String setEdit(@ModelAttribute Professor professor, @PathVariable Long id) {
        professor.setId(id);
        professorsService.addProfessor(professor);
        return "redirect:/professor/details/" + id;
    }

    @RequestMapping("/professor/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("professor", professorsService.getProfessor(id));
        return "professor/details";
    }

    @RequestMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorsService.deleteProfessor(id);
        return "redirect:/professor/list";
    }



}

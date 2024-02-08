package com.uniovi.sdi2324207spring.controllers;

import com.uniovi.sdi2324207spring.entities.Mark;
import com.uniovi.sdi2324207spring.entities.Professor;
import com.uniovi.sdi2324207spring.services.MarksService;
import com.uniovi.sdi2324207spring.services.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfessorsController {

    @Autowired //Inyectar el servicio
    private ProfessorsService professorsService;

    @RequestMapping("/professor/list")
    public String getList() {
        return professorsService.getProfessors().toString();
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public void addProfessor(@ModelAttribute Professor professor) {
        professorsService.addProfessor(professor);
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.GET)
    public void setProfessor(@ModelAttribute Professor professor) {

    }

    @RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.POST)
    public String editProfessor(@ModelAttribute Professor professor) {
        return professorsService.editProcessor(professor);
    }

    @RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.GET)
    public void editProfessorGet(@ModelAttribute Professor professor) {

    }

    @RequestMapping("/professor/details/{id}")
    public String getDetail(@PathVariable Long id) {
        Professor p = professorsService.getProfessor(id);
        return p.toString();
    }

    @RequestMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorsService.deleteProfessor(id);
        return "Ok";
    }



}

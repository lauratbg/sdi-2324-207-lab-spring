package com.uniovi.sdi2324207spring.services;

import com.uniovi.sdi2324207spring.entities.Mark;
import com.uniovi.sdi2324207spring.entities.Professor;
import com.uniovi.sdi2324207spring.repositories.MarksRepository;
import com.uniovi.sdi2324207spring.repositories.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {
//    private List<Professor> professors = new ArrayList<>();

//    @PostConstruct
//    public void init(){
//        professors.add(new Professor(1L, "71854996Y", "Laura", "Gómez", "Profesor titular"));
//        professors.add(new Professor(2L, "84585858K", "Miguel", "Gayol", "Catedrático"));
//    }

    @Autowired
    private ProfessorsRepository professorsRepository;

    public List<Professor> getProfessors() {
        List<Professor> professors = new ArrayList<Professor>();
        professorsRepository.findAll().forEach(professors::add);
        return professors;
    }

    public Professor getProfessor(Long id) {
        return professorsRepository.findById(id).get();
    }

    public Professor getProfessorByDni(String dni) {
        Professor p = null;
        for(int i = 0; i < getProfessors().size(); i++){
            if(getProfessors().get(i).getDni().equals(dni)){
                p = getProfessors().get(i);
            }
        }
        return p;
    }

    public void addProfessor(Professor professor) {
        professorsRepository.save(professor);
    }

//    public String editProcessor(Professor professor) {
//        return "edit";
//    }

    public void deleteProfessor(Long id) {
        professorsRepository.deleteById(id);

    }
}

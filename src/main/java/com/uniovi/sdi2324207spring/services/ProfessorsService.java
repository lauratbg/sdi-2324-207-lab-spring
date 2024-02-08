package com.uniovi.sdi2324207spring.services;

import com.uniovi.sdi2324207spring.entities.Mark;
import com.uniovi.sdi2324207spring.entities.Professor;
import com.uniovi.sdi2324207spring.repositories.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {
    private List<Professor> professors = new ArrayList<>();

    @PostConstruct
    public void init(){
        professors.add(new Professor(1L, "71854996Y", "Laura", "Gómez", "Profesor titular"));
        professors.add(new Professor(2L, "84585858K", "Miguel", "Gayol", "Catedrático"));

    }
    public ProfessorsService(List<Professor> professors) {
        this.professors = professors;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public Professor getProfessor(Long id) {
        for(Professor professor :  professors)
            if(professor.getId().equals(id))
                return professor;

        return null;
    }

    public String addProfessor(Professor professor) {
        if(professor.getId()==null){
            if(professors.isEmpty())
                professor.setId(1L);
            else
                professor.setId(professors.get(professors.size()-1).getId()+1);
        }
        professors.add(professor);

        return "add";
    }

    public String editProcessor(Professor professor) {
        return "edit";
    }

    public void deleteProfessor(Long id) {
//        if(!professors.isEmpty()){
//            for(Professor professor : professors)
//                if(professor.getId().equals(id))
//                    professors.remove(professor);
//        }
        professors.remove((getProfessor(id)));

    }
}

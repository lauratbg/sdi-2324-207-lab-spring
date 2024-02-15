package com.uniovi.sdi2324207spring.validators;

import com.uniovi.sdi2324207spring.entities.Professor;
import com.uniovi.sdi2324207spring.entities.User;
import com.uniovi.sdi2324207spring.services.ProfessorsService;
import com.uniovi.sdi2324207spring.services.UsersService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddProfessorFormValidator implements Validator {
    private final ProfessorsService professorsService;
    public AddProfessorFormValidator(ProfessorsService professorsService) {
        this.professorsService = professorsService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return Professor.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        if (professor.getDni().length() != 9) {
            errors.rejectValue("dni", "Error.addProfessor.dni.length");}

        if (!isChar(professor.getDni().charAt(professor.getDni().length()-1))) {
            errors.rejectValue("dni", "Error.addProfessor.dni.lastChar");}

        if (professorsService.getProfessorByDni(professor.getDni()) != null) {
            errors.rejectValue("dni", "Error.addProfessor.dni.duplicate");}

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Error.empty");

        if (professor.getName().endsWith(" ")|| professor.getName().startsWith(" ")) {
            errors.rejectValue("name", "Error.addProfessor.name.blankSpaces");}

        if (professor.getSurname().endsWith(" ")|| professor.getSurname().startsWith(" ")) {
            errors.rejectValue("surname", "Error.addProfessor.surname.blankSpaces");}

    }

    public boolean isChar(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
}

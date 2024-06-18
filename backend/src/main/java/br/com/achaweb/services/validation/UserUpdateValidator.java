package br.com.achaweb.services.validation;

import br.com.achaweb.controllers.exceptions.FieldMessage;
import br.com.achaweb.dto.UserDTO;
import br.com.achaweb.dto.UserUpdateDTO;
import br.com.achaweb.entities.User;
import br.com.achaweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserUpdateValid ann) {
    }


    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User user = repository.findByEmail(dto.getEmail());

        if(user != null) {
            list.add(new FieldMessage("email", "Email já existe"));
        }

        /*if() {
            list.add();
        }*/

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
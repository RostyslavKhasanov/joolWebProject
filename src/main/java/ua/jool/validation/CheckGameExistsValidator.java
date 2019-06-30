package ua.jool.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.jool.repository.GameRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CheckGameExistsValidator implements ConstraintValidator<CheckGameExists, String> {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (gameRepository.findByName(s) == null) {
            return true;
        }
        return false;
    }
}

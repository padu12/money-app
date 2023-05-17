package ua.odessa.moneyApp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.odessa.moneyApp.models.Person;

@Component
public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(!person.getPassword().equals(person.getRepeatPassword())) {
            errors.rejectValue("repeatPassword", "", "Passwords should be equal");
        }
    }
}

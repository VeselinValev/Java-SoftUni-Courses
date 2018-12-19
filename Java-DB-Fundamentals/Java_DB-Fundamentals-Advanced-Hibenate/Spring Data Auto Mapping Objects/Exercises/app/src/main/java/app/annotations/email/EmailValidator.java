package app.annotations.email;

import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmailValidator implements ConstraintValidator<Email, CharSequence> {

    @Override
    public void initialize(Email constraintAnnotation) {

    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        String email = String.valueOf(charSequence);
        return email.contains("@") && email.contains(".");
    }
}

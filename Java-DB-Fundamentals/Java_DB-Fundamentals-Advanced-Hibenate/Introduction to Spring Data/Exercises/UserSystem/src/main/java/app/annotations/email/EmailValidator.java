package app.annotations.email;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<Email, CharSequence> {

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^([A-Za-z\\d]+[.\\-_]?[A-Za-z\\d]+)@[A-Za-z]+-?[A-Za-z]+(\\.[A-Za-z\\-]+)+$");
        Matcher matcher = pattern.matcher(charSequence);
        return matcher.find();
    }
}

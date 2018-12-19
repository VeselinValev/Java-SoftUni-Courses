package app.annotations.password;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

    private int minLength;
    private boolean containsDigit;
    private boolean containsLowerCase;
    private boolean containsUpperCase;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.containsDigit = password.containsDigit();
        this.containsLowerCase = password.containsLowerCase();
        this.containsUpperCase = password.containsUpperCase();
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null || charSequence.length() < this.minLength){
            return false;
        }

        boolean hasDigit = false;
        boolean hasLower = false;
        boolean hasUpper = false;

        if (this.containsDigit){
            for (int i = 0; i < charSequence.length(); i++){
                if (Character.isDigit(charSequence.charAt(i))){
                    hasDigit = true;
                    break;
                }
            }
        }else{
            hasDigit = true;
        }

        if (this.containsLowerCase){
            for (int i = 0; i < charSequence.length(); i++){
                if (Character.isLowerCase(charSequence.charAt(i))){
                    hasLower = true;
                    break;
                }
            }
        }else{
            hasLower = true;
        }

        if (this.containsUpperCase){
            for (int i = 0; i < charSequence.length(); i++){
                if (Character.isUpperCase(charSequence.charAt(i))){
                    hasUpper = true;
                    break;
                }
            }
        }else{
            hasUpper = true;
        }

        return hasDigit && hasLower && hasUpper;
    }
}

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
    private int maxLength;
    private boolean containsDigit;
    private boolean containsLowerCase;
    private boolean containsUpperCase;
    private boolean containsSpecialSymbol;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.containsDigit = password.containsDigit();
        this.containsLowerCase = password.containsLowerCase();
        this.containsUpperCase = password.containsUpperCase();
        this.containsSpecialSymbol = password.containsSpecialSymbol();

    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null || charSequence.length() < this.minLength || charSequence.length() > this.maxLength){
            return false;
        }

        boolean hasDigit = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSpecial = false;

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

        if (this.containsSpecialSymbol){
            List<Character> specialSymbols = new ArrayList<>();
            Collections.addAll(specialSymbols, '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '<', '>', '?');
            for (int i = 0; i < charSequence.length(); i++){
                if (specialSymbols.contains(charSequence.charAt(i))){
                    hasSpecial = true;
                    break;
                }
            }
        }else{
            hasSpecial = true;
        }

        return hasDigit && hasLower && hasSpecial && hasUpper;
    }
}


package br.com.cadastroEmbarcacoes.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SenhaValidator implements ConstraintValidator<SenhaValidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) return false;
        if(value.contains(" ")) return false;
        return true;
    }
    
}
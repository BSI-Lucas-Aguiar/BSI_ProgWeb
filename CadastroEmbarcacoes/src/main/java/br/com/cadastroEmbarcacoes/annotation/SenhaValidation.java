
package br.com.cadastroEmbarcacoes.annotation;

import java.lang.annotation.*;
import javax.validation.*;


@Documented
@Constraint(validatedBy = SenhaValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhaValidation {
    String message() default "Senha inválida.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

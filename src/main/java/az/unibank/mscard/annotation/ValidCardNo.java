package az.unibank.mscard.annotation;


import az.unibank.mscard.validator.CardNoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CardNoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCardNo {

    String message() default "The list items is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

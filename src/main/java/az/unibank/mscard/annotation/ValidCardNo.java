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

    /**
     * Default error message if the items is not valid.
     *
     * @return : error message.
     */
    String message() default "The list items is not valid";

    /**
     * @return the groups the constraint belongs to
     */
    Class<?>[] groups() default {};

    /**
     * The payload of the parameter.
     *
     * @return the payload associated to the constraint
     */
    Class<? extends Payload>[] payload() default {};

}

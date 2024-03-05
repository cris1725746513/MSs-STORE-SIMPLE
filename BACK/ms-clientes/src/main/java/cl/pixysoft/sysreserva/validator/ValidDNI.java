package cl.pixysoft.sysreserva.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static jakarta.validation.constraintvalidation.ValidationTarget.ANNOTATED_ELEMENT;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = DNIValidator.class)
@Target({
        METHOD,
        FIELD,
        ANNOTATION_TYPE,
        CONSTRUCTOR,
        PARAMETER,
        TYPE_USE})
@Retention(RUNTIME)
@SupportedValidationTarget(ANNOTATED_ELEMENT)
@ReportAsSingleViolation
public @interface ValidDNI {

    String message() default "RUT no es valido";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}

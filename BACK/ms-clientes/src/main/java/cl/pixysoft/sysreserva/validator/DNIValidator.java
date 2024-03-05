package cl.pixysoft.sysreserva.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import cl.pixysoft.sysreserva.utils.Validadores;
public class DNIValidator implements ConstraintValidator<ValidDNI, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Validadores.VerificarCedula(value);
    }
}

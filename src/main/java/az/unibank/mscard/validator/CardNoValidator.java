package az.unibank.mscard.validator;


import az.unibank.mscard.annotation.ValidCardNo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;



@Component
public class CardNoValidator implements ConstraintValidator<ValidCardNo, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        return isCardNoValid(value);
    }

    boolean isCardNoValid(String cardNo) {
        int nDigits = cardNo.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {
            int d = cardNo.charAt(i) - '0';

            if (isSecond)
                d = d * 2;

            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }

}

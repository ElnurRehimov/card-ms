package az.unibank.mscard.exception;

import lombok.Getter;

@Getter
public class CardNotFoundException extends RuntimeException {

    private final String code;

    public CardNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

}

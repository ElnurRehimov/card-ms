package az.unibank.mscard.exception;

import lombok.Getter;

@Getter
public class CardAlreadyExistException extends RuntimeException {

    private final String code;

    public CardAlreadyExistException(String message, String code) {
        super(message);
        this.code = code;
    }

}

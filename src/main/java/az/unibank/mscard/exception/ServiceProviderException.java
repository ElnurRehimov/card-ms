package az.unibank.mscard.exception;

import lombok.Getter;

@Getter
public class ServiceProviderException extends RuntimeException {

    private final String code;

    public ServiceProviderException(String message, String code) {
        super(message);
        this.code = code;
    }

}

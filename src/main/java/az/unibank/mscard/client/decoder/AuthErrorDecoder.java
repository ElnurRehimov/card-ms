package az.unibank.mscard.client.decoder;

import az.unibank.mscard.exception.ServiceProviderException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
public class AuthErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UserErrorDetails errorDetails = objectMapper.readValue(bodyIs, UserErrorDetails.class);
            if (Objects.nonNull(errorDetails.message)) {
                if (errorDetails.getStatus() == null) {
                    return new ServiceProviderException(errorDetails.getMessage(), "409");
                }
                return new ServiceProviderException(errorDetails.getMessage(), errorDetails.getStatus().toString());
            }
        } catch (IOException e) {
            return new ServiceProviderException("Service not available", "503");
        }
        return new ServiceProviderException("Service not available", "500");
    }

    @Getter
    @Setter
    public static class UserErrorDetails {

        private Integer status;
        private String error;
        private String message;

    }

}


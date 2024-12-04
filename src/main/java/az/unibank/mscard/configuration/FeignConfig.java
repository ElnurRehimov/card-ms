package az.unibank.mscard.configuration;

import az.unibank.mscard.client.decoder.AuthErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new AuthErrorDecoder();
    }
}

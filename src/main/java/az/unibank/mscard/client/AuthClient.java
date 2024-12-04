package az.unibank.mscard.client;

import az.unibank.mscard.client.response.AuthDetailsResponse;
import az.unibank.mscard.configuration.FeignConfig;
import az.unibank.mscard.model.enums.Attribute;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "auth-client",
        url = "${feign.local.client.auth.url}",
        path = "${feign.local.client.auth.path}",
        configuration = FeignConfig.class)
public interface AuthClient {

    @GetMapping("/api/v1/auth/internal/check")
    ResponseEntity<AuthDetailsResponse> checkAuthentication(@RequestParam Attribute attribute, @RequestHeader("Authorization") String token);

}

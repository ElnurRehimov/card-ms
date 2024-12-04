package az.unibank.mscard.service.impl;

import az.unibank.mscard.client.AuthClient;
import az.unibank.mscard.client.response.AuthDetailsResponse;
import az.unibank.mscard.model.enums.Attribute;
import az.unibank.mscard.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    AuthClient authClient;

    @Override
    public AuthDetailsResponse getAuthDetails(String token, Attribute attribute) {
        return authClient.checkAuthentication(attribute,token).getBody();
    }
}

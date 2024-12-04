package az.unibank.mscard.service;

import az.unibank.mscard.client.response.AuthDetailsResponse;
import az.unibank.mscard.model.enums.Attribute;

public interface AuthService {

    AuthDetailsResponse getAuthDetails(String token, Attribute attribute);
}

package az.unibank.mscard.client.enums;

import az.unibank.mscard.model.enums.Attribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN(List.of(Attribute.GET_CARD, Attribute.INSERT_CARD, Attribute.DELETE_CARD, Attribute.UPDATE_CARD)),
    USER(List.of(Attribute.GET_CARD));

    private final List<Attribute> attributes;
}

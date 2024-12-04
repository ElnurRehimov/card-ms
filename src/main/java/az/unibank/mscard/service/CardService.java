package az.unibank.mscard.service;

import az.unibank.mscard.model.view.CardView;
import az.unibank.mscard.model.dto.CardRequestDto;

import java.util.List;

public interface CardService {

    List<CardView> getAllCards(String token);

    List<CardView> getAllCardsByUserId(String token,Long userId);

    CardView getById(String token, Long id);

    CardView create(String token,CardRequestDto requestDTO);

    CardView update(String token, Long id, CardRequestDto request);

    void delete(String token,Long id);

}

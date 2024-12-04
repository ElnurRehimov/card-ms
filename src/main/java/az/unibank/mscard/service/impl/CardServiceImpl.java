package az.unibank.mscard.service.impl;

import az.unibank.mscard.exception.CardAlreadyExistException;
import az.unibank.mscard.exception.CardNotFoundException;
import az.unibank.mscard.mapper.CardMapper;
import az.unibank.mscard.model.view.CardView;
import az.unibank.mscard.model.dto.CardRequestDto;
import az.unibank.mscard.model.entity.Card;
import az.unibank.mscard.model.enums.Attribute;
import az.unibank.mscard.repository.CardRepository;
import az.unibank.mscard.service.AuthService;
import az.unibank.mscard.service.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CardServiceImpl implements CardService {

    CardRepository cardRepository;
    AuthService authService;
    CardMapper cardMapper;

    @Override
    public List<CardView> getAllCards(String token) {

        authService.getAuthDetails(token, Attribute.GET_CARD);
        return cardRepository.findAll()
                .stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CardView> getAllCardsByUserId(String token,Long userId) {

        authService.getAuthDetails(token, Attribute.GET_CARD_USER_ID);
        return cardRepository.findAllByUserId(userId)
                .stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CardView getById(String token, Long id) {

        var auth = authService.getAuthDetails(token, Attribute.GET_CARD);
        return cardRepository.findByIdAndUserId(id, auth.getUserId())
                .map(cardMapper::toDto)
                .orElseThrow(() -> new CardNotFoundException("card was not found", "404"));
    }

    @Override
    public CardView create(String token, CardRequestDto requestDto) {

        var auth = authService.getAuthDetails(token, Attribute.INSERT_CARD);
        var card = cardRepository.findByPan(requestDto.getPan());
        if (card.isPresent()) {
            throw new CardAlreadyExistException("this pan already exist", "409");
        }
        var cardEntity = cardMapper.toEntity(requestDto, auth.getUserId());
        return cardMapper.toDto(cardRepository.save(cardEntity));
    }

    @Override
    public CardView update(String token, Long id, CardRequestDto request) {

        var auth = authService.getAuthDetails(token, Attribute.UPDATE_CARD);
        var card = cardRepository.findByIdAndUserId(id, auth.getUserId())
                .orElseThrow(() -> new CardNotFoundException("card was not found", "404"));
        var cardEntity = cardMapper.toEntity(card, request, auth.getUserId());
        return cardMapper.toDto(cardRepository.save(cardEntity));
    }

    @Override
    public void delete(String token, Long id) {

        var auth = authService.getAuthDetails(token, Attribute.DELETE_CARD);
        Card card = cardRepository.findByIdAndUserId(id, auth.getUserId())
                .orElseThrow(() -> new CardNotFoundException("Card not found", "404"));
        cardRepository.delete(card);
    }
}

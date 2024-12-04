package az.unibank.mscard.controller;

import az.unibank.mscard.model.view.CardView;
import az.unibank.mscard.model.dto.CardRequestDto;
import az.unibank.mscard.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardView>> getAllCards(HttpServletRequest request) {

        return ResponseEntity.ok(cardService.getAllCards(request.getHeader(HttpHeaders.AUTHORIZATION)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CardView>> getAllCardsByUserId(@PathVariable Long userId, HttpServletRequest request) {

        return ResponseEntity.ok(cardService.getAllCardsByUserId(request.getHeader(HttpHeaders.AUTHORIZATION),userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardView> getById(@PathVariable Long id, HttpServletRequest request) {

        return ResponseEntity.ok(cardService.getById(request.getHeader(HttpHeaders.AUTHORIZATION), id));
    }

    @PostMapping
    public ResponseEntity<CardView> create(@Valid @RequestBody CardRequestDto cardRequestDto, HttpServletRequest request) {
        cardService.create(request.getHeader(HttpHeaders.AUTHORIZATION), cardRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardView> update(
            @PathVariable Long id, @Valid @RequestBody CardRequestDto cardRequestDto,
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(cardService.update(request.getHeader(HttpHeaders.AUTHORIZATION), id, cardRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CardView> delete(@PathVariable Long id, HttpServletRequest request) {
        cardService.delete(request.getHeader(HttpHeaders.AUTHORIZATION), id);
        return ResponseEntity.ok().build();
    }
}

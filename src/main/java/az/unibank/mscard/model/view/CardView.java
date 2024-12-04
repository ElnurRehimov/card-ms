package az.unibank.mscard.model.view;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardView {

    private Long id;
    private String pan;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime expiryDate;
}

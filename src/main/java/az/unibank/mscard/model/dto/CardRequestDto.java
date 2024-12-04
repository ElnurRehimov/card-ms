package az.unibank.mscard.model.dto;

import az.unibank.mscard.annotation.ValidCardNo;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CardRequestDto {

    @Size(max = 15)
    @ValidCardNo
    private String pan;
    private String embossedName;
    private LocalDateTime expiryDate;


}

package az.unibank.mscard.mapper;


import az.unibank.mscard.model.view.CardView;
import az.unibank.mscard.model.dto.CardRequestDto;
import az.unibank.mscard.model.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {

    CardView toDto(Card card);

    Card toEntity(CardRequestDto cardRequestDto, Long userId);

    Card toEntity(@MappingTarget Card card, CardRequestDto cardRequestDto, Long userId);

}

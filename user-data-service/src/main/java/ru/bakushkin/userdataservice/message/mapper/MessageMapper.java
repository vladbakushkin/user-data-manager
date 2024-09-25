package ru.bakushkin.userdataservice.message.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.bakushkin.dto.message.MessageRequestDto;
import ru.bakushkin.dto.message.MessageResponseDto;
import ru.bakushkin.dto.message.MessageShortResponseDto;
import ru.bakushkin.userdataservice.message.entity.Message;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    Message toMessage(MessageRequestDto messageRequestDto);

    MessageResponseDto toMessageResponseDto(Message message);

    MessageShortResponseDto toMessageShortResponseDto(Message message);
}

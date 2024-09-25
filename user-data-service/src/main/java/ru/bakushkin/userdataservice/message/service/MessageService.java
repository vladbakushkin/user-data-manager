package ru.bakushkin.userdataservice.message.service;

import ru.bakushkin.dto.message.MessageRequestDto;
import ru.bakushkin.dto.message.MessageResponseDto;

import java.util.List;

public interface MessageService {

    void saveMessage(MessageRequestDto messageRequestDto);

    List<MessageResponseDto> getMessagesFromUser(Long userId);

    void deleteMessage(Long messageId);
}

package ru.bakushkin.userdataservice.message.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bakushkin.dto.message.MessageResponseDto;
import ru.bakushkin.userdataservice.message.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
@Slf4j
@Tag(name = "API сервера для взаимодействия с сообщениями пользователей")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{userId}")
    public List<MessageResponseDto> getMessagesFromUser(@PathVariable Long userId) {
        log.info("Getting all messages from user: {}", userId);
        return messageService.getMessagesFromUser(userId);
    }

    @DeleteMapping("/{messageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable Long messageId) {
        log.info("Deleting message by id: {}", messageId);
        messageService.deleteMessage(messageId);
    }
}

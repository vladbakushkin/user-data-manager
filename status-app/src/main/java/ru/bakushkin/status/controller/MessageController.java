package ru.bakushkin.status.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bakushkin.dto.message.MessageRequestDto;
import ru.bakushkin.status.service.MessageService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("messages")
@Slf4j
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@Valid @RequestBody MessageRequestDto messageRequestDto)
            throws JsonProcessingException {
        log.info("Sending message: {}", messageRequestDto);
        messageService.sendMessage(messageRequestDto);
        return ResponseEntity.ok("Message sent: " + messageRequestDto);
    }
}

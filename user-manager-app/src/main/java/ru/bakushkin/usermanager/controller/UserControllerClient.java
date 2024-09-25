package ru.bakushkin.usermanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bakushkin.dto.message.MessageResponseDto;
import ru.bakushkin.dto.user.UserResponseDto;
import ru.bakushkin.usermanager.client.UserServiceClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Tag(name = "API клиента для взаимодействия с сервером")
public class UserControllerClient {

    private final UserServiceClient userServiceClient;

    @Operation(summary = "Получение списка пользователей",
            description = "Возвращает список пользователей с поддержкой пагинации.")
    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers(@RequestParam(defaultValue = "0") Integer from,
                                             @RequestParam(defaultValue = "10") Integer size) {
        return userServiceClient.getAllUsers(from, size);
    }

    @Operation(summary = "Получение списка активных пользователей",
            description = "Возвращает список всех активных пользователей системы.")
    @GetMapping("/users/active")
    public List<UserResponseDto> getActiveUsers() {
        return userServiceClient.getActiveUsers();
    }

    @Operation(summary = "Получение сообщений пользователя",
            description = "Возвращает список сообщений, отправленных пользователем с указанным ID.")
    @GetMapping("/messages/{userId}")
    public List<MessageResponseDto> getMessagesFromUser(@PathVariable Long userId) {
        return userServiceClient.getMessagesFromUser(userId);
    }

    @Operation(summary = "Удаление пользователя по ID",
            description = "Удаляет пользователя с указанным ID.")
    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userServiceClient.deleteUser(userId);
    }

    @Operation(summary = "Удаление сообщения по ID",
            description = "Удаляет сообщение с указанным ID.")
    @DeleteMapping("/messages/{messageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable Long messageId) {
        userServiceClient.deleteMessage(messageId);
    }
}

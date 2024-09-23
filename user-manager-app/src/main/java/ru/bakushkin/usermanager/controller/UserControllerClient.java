package ru.bakushkin.usermanager.controller;

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
public class UserControllerClient {

    private final UserServiceClient userServiceClient;

    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers(@RequestParam(defaultValue = "0") Integer from,
                                             @RequestParam(defaultValue = "10") Integer size) {
        return userServiceClient.getAllUsers(from, size);
    }

    @GetMapping("/users/active")
    public List<UserResponseDto> getActiveUsers() {
        return userServiceClient.getActiveUsers();
    }

    @GetMapping("/messages/{userId}")
    public List<MessageResponseDto> getMessagesFromUser(@PathVariable Long userId) {
        return userServiceClient.getMessagesFromUser(userId);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userServiceClient.deleteUser(userId);
    }

    @DeleteMapping("/messages/{messageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable Long messageId) {
        userServiceClient.deleteMessage(messageId);
    }
}

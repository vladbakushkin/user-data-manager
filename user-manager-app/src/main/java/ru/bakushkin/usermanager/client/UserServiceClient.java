package ru.bakushkin.usermanager.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bakushkin.dto.message.MessageResponseDto;
import ru.bakushkin.dto.user.UserResponseDto;

import java.util.List;

@FeignClient(name = "${application.client.name}", url = "${application.client.url}")
public interface UserServiceClient {

    @GetMapping("/users")
    List<UserResponseDto> getAllUsers(@RequestParam(defaultValue = "0") Integer from,
                                      @RequestParam(defaultValue = "10") Integer size);

    @GetMapping("/users/active")
    List<UserResponseDto> getActiveUsers();

    @GetMapping("/messages/{userId}")
    List<MessageResponseDto> getMessagesFromUser(@PathVariable Long userId);

    @DeleteMapping("/users/{userId}")
    void deleteUser(@PathVariable Long userId);

    @DeleteMapping("/messages/{messageId}")
    void deleteMessage(@PathVariable Long messageId);
}

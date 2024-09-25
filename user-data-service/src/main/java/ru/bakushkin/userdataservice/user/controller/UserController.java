package ru.bakushkin.userdataservice.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bakushkin.dto.user.UserResponseDto;
import ru.bakushkin.userdataservice.user.service.UserService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
@Tag(name = "API сервера для взаимодействия с пользователями")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Получение списка пользователей",
            description = "Возвращает список пользователей с поддержкой пагинации.")
    @GetMapping
    public List<UserResponseDto> getAllUsers(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                             @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Getting all users with pagination: from {}, size {}", from, size);
        return userService.getAllUsers(from, size);
    }

    @Operation(summary = "Получение списка активных пользователей",
            description = "Возвращает список всех активных пользователей системы.")
    @GetMapping("/active")
    public List<UserResponseDto> getActiveUsers() {
        log.info("Getting active users");
        return userService.getActiveUsers();
    }

    @Operation(summary = "Удаление пользователя по ID",
            description = "Удаляет пользователя с указанным ID.")
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        log.info("Deleting user by id: {}", userId);
        userService.deleteUser(userId);
    }
}

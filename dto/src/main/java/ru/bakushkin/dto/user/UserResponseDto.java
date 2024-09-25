package ru.bakushkin.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bakushkin.dto.message.MessageShortResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Полная информация о пользователе")
public class UserResponseDto {

    @Schema(description = "Уникальный идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "Адрес электронной почты пользователя", example = "example@gmail.com")
    private String email;

    @Schema(description = "Дата и время создания пользователя", example = "2024-09-25T19:51:46")
    private LocalDateTime createdAt;

    @Schema(description = "Список сообщений пользователя")
    private List<MessageShortResponseDto> messages;
}

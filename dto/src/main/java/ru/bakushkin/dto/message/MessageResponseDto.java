package ru.bakushkin.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bakushkin.dto.user.UserShortResponseDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Полная информация о сообщении пользователя")
public class MessageResponseDto {

    private Long id;

    private UserShortResponseDto user;

    private String messageContent;

    private LocalDateTime receivedAt;
}

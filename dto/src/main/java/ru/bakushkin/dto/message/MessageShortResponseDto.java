package ru.bakushkin.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Краткая информация о сообщении пользователя")
public class MessageShortResponseDto {

    private Long id;

    private String messageContent;

    private LocalDateTime receivedAt;
}

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

    @Schema(description = "Уникальный идентификатор сообщения", example = "1")
    private Long id;

    @Schema(description = "Содержимое сообщения", example = "from postman")
    private String messageContent;

    @Schema(description = "Дата и время получения сообщения", example = "2024-09-25T19:51:46")
    private LocalDateTime receivedAt;
}

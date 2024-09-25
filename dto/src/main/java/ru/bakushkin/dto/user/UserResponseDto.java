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

    private Long id;

    private String email;

    private LocalDateTime createdAt;

    private List<MessageShortResponseDto> messages;
}

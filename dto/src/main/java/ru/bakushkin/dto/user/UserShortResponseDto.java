package ru.bakushkin.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Краткая информация о пользователя")
public class UserShortResponseDto {

    private Long id;

    private String email;

    private LocalDateTime createdAt;

}

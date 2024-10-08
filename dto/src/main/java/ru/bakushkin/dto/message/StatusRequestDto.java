package ru.bakushkin.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сообщение о статусе микросервиса")
public class StatusRequestDto {

    private String applicationId;

    private String status;

    private LocalDateTime sendTime;
}

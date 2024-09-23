package ru.bakushkin.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusRequestDto {

    private String applicationId;

    private String status;

    private LocalDateTime sendTime;
}

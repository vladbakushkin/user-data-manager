package ru.bakushkin.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDto {

    private String email;

    private String message;

//    private LocalDateTime sendTime = LocalDateTime.now();
}

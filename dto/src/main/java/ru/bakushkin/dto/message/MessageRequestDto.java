package ru.bakushkin.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сообщение с данными для шины RabbitMQ")
public class MessageRequestDto {

    @Schema(description = "Адрес электронной почты", example = "example@gmail.com")
    @Size(min = 6, max = 255, message = "Адрес электронной почты должен содержать от 6 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате example@gmail.com")
    private String email;

    @Schema(description = "Содержимое сообщения", example = "from postman")
    @NotBlank(message = "Сообщение не может быть пустым")
    @Size(max = 500, message = "Сообщение не может быть больше 500 символов")
    private String messageContent;
}

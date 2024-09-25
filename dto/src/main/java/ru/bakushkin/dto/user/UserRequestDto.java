package ru.bakushkin.dto.user;

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
@Schema(description = "Сохранение пользователя")
public class UserRequestDto {

    @Schema(description = "Адрес электронной почты", example = "example@gmail.com")
    @Size(min = 6, max = 255, message = "Адрес электронной почты должен содержать от 6 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате example@gmail.com")
    private String email;
}

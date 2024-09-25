package ru.bakushkin.userdataservice.user.service;

import ru.bakushkin.dto.user.UserRequestDto;
import ru.bakushkin.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    void saveUser(UserRequestDto userRequestDto);

    boolean checkUserExistsByEmail(String email);

    List<UserResponseDto> getAllUsers(Integer from, Integer size);

    List<UserResponseDto> getActiveUsers();

    void deleteUser(Long userId);
}

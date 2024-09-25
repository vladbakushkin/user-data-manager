package ru.bakushkin.userdataservice.user.mapper;

import org.mapstruct.Mapper;
import ru.bakushkin.dto.user.UserRequestDto;
import ru.bakushkin.dto.user.UserResponseDto;
import ru.bakushkin.dto.user.UserShortResponseDto;
import ru.bakushkin.userdataservice.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequestDto userRequestDto);

    UserResponseDto toUserResponseDto(User user);

    UserShortResponseDto toUserShortResponseDto(User user);
}

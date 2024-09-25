package ru.bakushkin.userdataservice.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakushkin.dto.message.MessageShortResponseDto;
import ru.bakushkin.dto.user.UserRequestDto;
import ru.bakushkin.dto.user.UserResponseDto;
import ru.bakushkin.userdataservice.message.entity.Message;
import ru.bakushkin.userdataservice.message.mapper.MessageMapper;
import ru.bakushkin.userdataservice.message.repository.MessageRepository;
import ru.bakushkin.userdataservice.service_activity.entity.ServiceActivityAction;
import ru.bakushkin.userdataservice.service_activity.service.ServiceActivityService;
import ru.bakushkin.userdataservice.user.entity.User;
import ru.bakushkin.userdataservice.user.mapper.UserMapper;
import ru.bakushkin.userdataservice.user.repository.UserRepository;
import ru.bakushkin.userdataservice.user_activity.entity.UserActivityAction;
import ru.bakushkin.userdataservice.user_activity.service.UserActivityService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    private final ServiceActivityService serviceActivityService;
    private final UserActivityService userActivityService;

    private final UserMapper userMapper;
    private final MessageMapper messageMapper;

    @Override
    @Transactional
    public void saveUser(UserRequestDto userRequestDto) {
        User user = userMapper.toUser(userRequestDto);
        User savedUser = userRepository.save(user);

        log.info("saved user: {}", savedUser);
        userActivityService.saveUserActivity(savedUser, UserActivityAction.CREATED);
    }

    public boolean checkUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public List<UserResponseDto> getAllUsers(Integer from, Integer size) {
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size);

        List<User> users = userRepository.findAll(pageable).getContent();

        List<Message> messages = messageRepository.findAllByUserIn(users);

        List<UserResponseDto> userResponseDtoList = users.stream()
                .map(user -> makeUserResponseDtoWithMessages(user, messages))
                .collect(Collectors.toList());

        log.info("got users: {}", userResponseDtoList);
        serviceActivityService.saveServiceActivity(ServiceActivityAction.GET_ALL_USERS);

        return userResponseDtoList;
    }

    @Override
    @Transactional
    public List<UserResponseDto> getActiveUsers() {

        // выбрать все сообщения, которые не старше 60 секунд
        LocalDateTime now = LocalDateTime.now();
        List<Message> lastMessages = messageRepository.findAllByReceivedAtAfter(now.minusSeconds(60));

        List<User> activeUsers = lastMessages.stream()
                .map(Message::getUser)
                .distinct()
                .collect(Collectors.toList());

        List<Message> messages = messageRepository.findAllByUserIn(activeUsers);

        List<UserResponseDto> userResponseDtoList = activeUsers.stream()
                .map(user -> makeUserResponseDtoWithMessages(user, messages))
                .collect(Collectors.toList());

        log.info("got active users: {}", activeUsers);
        serviceActivityService.saveServiceActivity(ServiceActivityAction.GET_ACTIVE_USERS);

        return userResponseDtoList;
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found."));
        userRepository.deleteById(userId);

        log.info("deleted user: {}", userId);
        serviceActivityService.saveServiceActivity(ServiceActivityAction.DELETE_USER);
    }

    private UserResponseDto makeUserResponseDtoWithMessages(User user, List<Message> messages) {
        UserResponseDto userResponseDto = userMapper.toUserResponseDto(user);

        List<MessageShortResponseDto> messageDtoList = messages.stream()
                .filter(message -> message.getUser().getId().equals(user.getId()))
                .map(messageMapper::toMessageShortResponseDto)
                .collect(Collectors.toList());

        userResponseDto.setMessages(messageDtoList);

        return userResponseDto;
    }
}

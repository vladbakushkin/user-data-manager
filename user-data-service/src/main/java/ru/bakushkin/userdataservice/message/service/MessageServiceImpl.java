package ru.bakushkin.userdataservice.message.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakushkin.dto.message.MessageRequestDto;
import ru.bakushkin.dto.message.MessageResponseDto;
import ru.bakushkin.dto.user.UserShortResponseDto;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    private final ServiceActivityService serviceActivityService;
    private final UserActivityService userActivityService;

    private final UserMapper userMapper;
    private final MessageMapper messageMapper;

    @Override
    @Transactional
    public void saveMessage(MessageRequestDto messageRequestDto) {
        Message message = messageMapper.toMessage(messageRequestDto);
        User user = userRepository.findByEmail(messageRequestDto.getEmail());
        message.setUser(user);

        Message savedMessage = messageRepository.save(message);

        log.info("saved message: {}", savedMessage);
        userActivityService.saveUserActivity(savedMessage.getUser(), UserActivityAction.SEND_MESSAGE);
    }

    @Override
    @Transactional
    public List<MessageResponseDto> getMessagesFromUser(Long userId) {

        List<Message> messages = messageRepository.findAllMessageByUserId(userId);

        List<MessageResponseDto> messageResponseDtoList = messages.stream()
                .map(message -> {
                    MessageResponseDto messageResponseDto = messageMapper.toMessageResponseDto(message);
                    UserShortResponseDto userShortResponseDto = userMapper.toUserShortResponseDto(message.getUser());
                    messageResponseDto.setUser(userShortResponseDto);
                    return messageResponseDto;
                })
                .collect(Collectors.toList());

        log.info("got messages from user: {}", messageResponseDtoList);
        serviceActivityService.saveServiceActivity(ServiceActivityAction.GET_MESSAGES_FROM_USER);

        return messageResponseDtoList;
    }

    @Override
    @Transactional
    public void deleteMessage(Long messageId) {
        messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message with id " + messageId + " not found."));
        messageRepository.deleteById(messageId);

        log.info("deleted message: {}", messageId);
        serviceActivityService.saveServiceActivity(ServiceActivityAction.DELETE_MESSAGE);
    }
}

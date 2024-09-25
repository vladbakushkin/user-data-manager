package ru.bakushkin.userdataservice.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.bakushkin.dto.message.MessageRequestDto;
import ru.bakushkin.dto.message.StatusRequestDto;
import ru.bakushkin.dto.user.UserRequestDto;
import ru.bakushkin.userdataservice.message.service.MessageService;
import ru.bakushkin.userdataservice.service_activity.service.ServiceActivityService;
import ru.bakushkin.userdataservice.user.service.UserService;

import static ru.bakushkin.dto.constant.Constants.QUEUE_MESSAGE;
import static ru.bakushkin.dto.constant.Constants.QUEUE_STATUS;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserMessageHandler {

    private final ObjectMapper objectMapper;

    private final UserService userService;

    private final MessageService messageService;

    private final ServiceActivityService serviceActivityService;

    @RabbitListener(queues = QUEUE_MESSAGE)
    public void receiveMessage(Message message) throws JsonProcessingException {

        byte[] body = message.getBody();
        String jsonBody = new String(body);

        MessageRequestDto messageRequestDto = objectMapper.readValue(jsonBody, MessageRequestDto.class);

        log.info("received message: {}", messageRequestDto);

        if (!userService.checkUserExistsByEmail(messageRequestDto.getEmail())) {
            UserRequestDto userRequestDto = new UserRequestDto();
            userRequestDto.setEmail(messageRequestDto.getEmail());
            userService.saveUser(userRequestDto);
        }

        if (messageRequestDto.getMessageContent() != null) {
            messageService.saveMessage(messageRequestDto);
        }
    }

    @RabbitListener(queues = QUEUE_STATUS)
    public void receiveStatus(Message message) throws JsonProcessingException {
        byte[] body = message.getBody();
        String jsonBody = new String(body);

        StatusRequestDto statusRequestDto = objectMapper.readValue(jsonBody, StatusRequestDto.class);

        log.info("received status: {}", statusRequestDto);

        serviceActivityService.saveServiceActivity(statusRequestDto);
    }
}

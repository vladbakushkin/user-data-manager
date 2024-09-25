package ru.bakushkin.status.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.bakushkin.dto.message.MessageRequestDto;
import ru.bakushkin.dto.message.StatusRequestDto;

import java.time.LocalDateTime;

import static ru.bakushkin.dto.constant.Constants.QUEUE_MESSAGE;
import static ru.bakushkin.dto.constant.Constants.QUEUE_STATUS;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class MessageService {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    @Value("${application.ms1.id}")
    private String appId1;

    @Value("${application.ms1.status}")
    private String appStatus1;

    @Value("${application.ms2.id}")
    private String appId2;

    @Value("${application.ms2.status}")
    private String appStatus2;

    @Value("${application.ms3.id}")
    private String appId3;

    @Value("${application.ms3.status}")
    private String appStatus3;

    public void sendStatus(String appId, String appStatus) throws JsonProcessingException {
        StatusRequestDto statusRequestDto = new StatusRequestDto(appId, appStatus, LocalDateTime.now());
        rabbitTemplate.convertAndSend(QUEUE_STATUS, objectMapper.writeValueAsString(statusRequestDto));
    }

    @Scheduled(fixedRate = 10000)
    public void sendStatusApp1() throws JsonProcessingException {
        sendStatus(appId1, appStatus1);
    }

    @Scheduled(fixedRate = 5000)
    public void sendStatusApp2() throws JsonProcessingException {
        sendStatus(appId2, appStatus2);
    }

    @Scheduled(fixedRate = 15000)
    public void sendStatusApp3() throws JsonProcessingException {
        sendStatus(appId3, appStatus3);
    }

    public void sendMessage(MessageRequestDto messageRequestDto) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(QUEUE_MESSAGE, objectMapper.writeValueAsString(messageRequestDto));
    }
}

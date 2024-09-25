package ru.bakushkin.userdataservice.service_activity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakushkin.dto.message.StatusRequestDto;
import ru.bakushkin.userdataservice.service_activity.entity.ServiceActivityAction;
import ru.bakushkin.userdataservice.service_activity.entity.ServiceActivityStatus;
import ru.bakushkin.userdataservice.service_activity.entity.ServiceActivity;
import ru.bakushkin.userdataservice.service_activity.repository.ServiceActivityRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ServiceActivityServiceImpl implements ServiceActivityService {

    @Value("${application.microservice3.id}")
    private Integer microservice3Id;

    private final ServiceActivityRepository serviceActivityRepository;

    @Override
    @Transactional
    public void saveServiceActivity(StatusRequestDto statusRequestDto) {
        ServiceActivity serviceActivity = new ServiceActivity();
        serviceActivity.setServiceId(Integer.valueOf(statusRequestDto.getApplicationId()));
        serviceActivity.setStatus(ServiceActivityStatus.valueOf(statusRequestDto.getStatus()));
        serviceActivity.setActivity(ServiceActivityAction.SEND_MESSAGE);
        serviceActivity.setActivityTime(statusRequestDto.getSendTime());

        serviceActivityRepository.save(serviceActivity);
    }

    @Override
    @Transactional
    public void saveServiceActivity(ServiceActivityAction serviceActivityAction) {
        ServiceActivity serviceActivity = new ServiceActivity();
        serviceActivity.setServiceId(microservice3Id);
        serviceActivity.setStatus(ServiceActivityStatus.ACTIVE);
        serviceActivity.setActivity(serviceActivityAction);
        serviceActivity.setActivityTime(LocalDateTime.now());

        serviceActivityRepository.save(serviceActivity);
    }
}

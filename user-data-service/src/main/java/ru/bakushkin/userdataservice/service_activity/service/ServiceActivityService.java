package ru.bakushkin.userdataservice.service_activity.service;

import ru.bakushkin.dto.message.StatusRequestDto;
import ru.bakushkin.userdataservice.service_activity.entity.ServiceActivityAction;

public interface ServiceActivityService {

    void saveServiceActivity(StatusRequestDto saveStatusRequestDto);

    void saveServiceActivity(ServiceActivityAction serviceActivityAction);
}

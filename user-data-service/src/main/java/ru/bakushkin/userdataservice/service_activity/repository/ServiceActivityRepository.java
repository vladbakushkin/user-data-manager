package ru.bakushkin.userdataservice.service_activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bakushkin.userdataservice.service_activity.entity.ServiceActivity;

public interface ServiceActivityRepository extends JpaRepository<ServiceActivity, Long> {
}

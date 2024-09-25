package ru.bakushkin.userdataservice.user_activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bakushkin.userdataservice.user_activity.entity.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
}

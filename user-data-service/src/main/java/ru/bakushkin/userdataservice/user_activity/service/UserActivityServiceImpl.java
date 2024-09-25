package ru.bakushkin.userdataservice.user_activity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakushkin.userdataservice.user_activity.entity.UserActivity;
import ru.bakushkin.userdataservice.user_activity.entity.UserActivityAction;
import ru.bakushkin.userdataservice.user_activity.repository.UserActivityRepository;
import ru.bakushkin.userdataservice.user.entity.User;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserActivityServiceImpl implements UserActivityService {

    private final UserActivityRepository userActivityRepository;

    @Override
    @Transactional
    public void saveUserActivity(User user, UserActivityAction userActivityAction) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUser(user);
        userActivity.setActivity(userActivityAction);
        userActivity.setActivityTime(LocalDateTime.now());

        userActivityRepository.save(userActivity);
    }
}

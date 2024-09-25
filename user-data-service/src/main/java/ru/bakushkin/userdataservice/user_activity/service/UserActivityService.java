package ru.bakushkin.userdataservice.user_activity.service;

import ru.bakushkin.userdataservice.user.entity.User;
import ru.bakushkin.userdataservice.user_activity.entity.UserActivityAction;

public interface UserActivityService {

    void saveUserActivity(User user, UserActivityAction userActivityAction);
}

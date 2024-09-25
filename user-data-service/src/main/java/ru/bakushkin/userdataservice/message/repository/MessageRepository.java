package ru.bakushkin.userdataservice.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bakushkin.userdataservice.message.entity.Message;
import ru.bakushkin.userdataservice.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllMessageByUserId(Long userId);

    List<Message> findAllByReceivedAtAfter(LocalDateTime after);

    List<Message> findAllByUserIn(List<User> users);
}

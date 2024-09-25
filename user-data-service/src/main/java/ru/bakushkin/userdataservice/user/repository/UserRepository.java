package ru.bakushkin.userdataservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bakushkin.userdataservice.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);
}

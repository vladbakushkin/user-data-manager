package ru.bakushkin.userdataservice.message.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.bakushkin.userdataservice.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_content", nullable = false)
    private String messageContent;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "received_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime receivedAt;
}

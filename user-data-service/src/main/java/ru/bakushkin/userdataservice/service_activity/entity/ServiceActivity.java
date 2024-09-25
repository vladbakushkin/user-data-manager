package ru.bakushkin.userdataservice.service_activity.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_activities")
public class ServiceActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceActivityAction activity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceActivityStatus status = ServiceActivityStatus.INACTIVE;

    @JoinColumn(name = "service_id", nullable = false)
    private Integer serviceId;

    @Column(name = "activity_time", nullable = false)
    private LocalDateTime activityTime;
}

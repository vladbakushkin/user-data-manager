package ru.bakushkin.status.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ru.bakushkin.dto.constant.Constants.QUEUE_MESSAGE;
import static ru.bakushkin.dto.constant.Constants.QUEUE_STATUS;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public Queue statusQueue() {
        return new Queue(QUEUE_STATUS, false);
    }

    @Bean
    public Queue messageQueue() {
        return new Queue(QUEUE_MESSAGE, false);
    }
}

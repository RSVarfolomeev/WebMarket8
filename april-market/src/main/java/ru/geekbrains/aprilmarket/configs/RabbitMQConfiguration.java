package ru.geekbrains.aprilmarket.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${spring.rabbitmq.durable: true}")
    private boolean durable;

    @Value("${spring.rabbitmq.send-order-queue: market.orders}")
    private String orderQueueName;

    @Bean
    public Queue orderQueue() {
        return new Queue(orderQueueName, durable);
    }

}

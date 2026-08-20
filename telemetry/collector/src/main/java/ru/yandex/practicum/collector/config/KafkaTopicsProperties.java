package ru.yandex.practicum.collector.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "collector.kafka.topics")
public class KafkaTopicsProperties {
    private String sensors;
    private String hubs;
}
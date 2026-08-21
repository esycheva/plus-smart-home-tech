package ru.yandex.practicum.collector.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.collector.config.KafkaTopicsProperties;
import ru.yandex.practicum.collector.mapper.EventMapper;
import ru.yandex.practicum.collector.model.HubEvent;
import ru.yandex.practicum.collector.model.SensorEvent;

@Service
@RequiredArgsConstructor
public class EventService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTopicsProperties topics;
    private final EventMapper eventMapper;

    public void collectSensorEvent(SensorEvent event) {
        kafkaTemplate.send(
                topics.getSensors(),
                event.getHubId(),
                eventMapper.toAvro(event)
        );
    }

    public void collectHubEvent(HubEvent event) {
        kafkaTemplate.send(
                topics.getHubs(),
                event.getHubId(),
                eventMapper.toAvro(event)
        );
    }
}

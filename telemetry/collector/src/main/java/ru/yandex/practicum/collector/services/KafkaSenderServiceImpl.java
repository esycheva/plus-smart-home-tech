package ru.yandex.practicum.collector.services;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.collector.TelemetryTopics;
import ru.yandex.practicum.collector.hubs.HubEvent;
import ru.yandex.practicum.collector.mapper.HubMapper;
import ru.yandex.practicum.collector.mapper.SensorMapper;
import ru.yandex.practicum.collector.sensors.SensorEvent;

import java.time.Duration;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaSenderServiceImpl implements KafkaSenderService {
    private final Producer<String, SpecificRecordBase> producer;

    @Override
    public void collectSensorEvent(SensorEvent sensorEvent) {
        log.trace("Произошло событие датчика - {}", sensorEvent);
        SpecificRecordBase sensor = SensorMapper.toAvro(sensorEvent);
        log.debug("Данные датчика преобразованы в тип SpecificRecordBase - {}", sensor);
        ProducerRecord<String, SpecificRecordBase> record = new ProducerRecord<>(TelemetryTopics.TELEMETRY_SENSORS_TOPIC, sensorEvent.getId(), sensor);
        producer.send(record);
        log.debug("Создана и отправлена запись данных датчика в Kafka - {}", record);
    }

    @Override
    public void collectHubEvent(HubEvent hubEvent) {
        log.trace("Произошло событие датчика - {}", hubEvent);
        SpecificRecordBase hub = HubMapper.toAvro(hubEvent);
        log.debug("Данные хаба/сценария преобразованы в тип SpecificRecordBase - {}", hub);
        ProducerRecord<String, SpecificRecordBase> record = new ProducerRecord<>(TelemetryTopics.TELEMETRY_HUBS_TOPIC, hubEvent.getHubId(), hub);
        producer.send(record);
        log.debug("Создана и отправлена запись данных хаба/сценария в Kafka - {}", record);
    }

    @PreDestroy
    public void destroy() {
        producer.flush();
        producer.close(Duration.ofSeconds(10));
        log.debug("Продюсер закрыт");
    }
}

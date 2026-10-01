package ru.yandex.practicum.telemetry.hadlers.snapshot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.enums.ConditionType;
import ru.yandex.practicum.kafka.telemetry.event.SensorStateAvro;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorAvro;
import ru.yandex.practicum.telemetry.service.functions.Metrics;

@Component
@RequiredArgsConstructor
public class TemperatureSensorEventHandler implements SnapshotHandler {
    @Override
    public Class<?> getSensorDataClass() {
        return SensorClasses.TEMPERATURE_SENSOR.getSensorClass();
    }

    @Override
    public int handle(ConditionType conditionType, SensorStateAvro sensorsState) {
        TemperatureSensorAvro data = (TemperatureSensorAvro) sensorsState.getData();
        return Metrics.TEMPERATURE_SENSORS_METRICS.get(conditionType).apply(data);
    }
}

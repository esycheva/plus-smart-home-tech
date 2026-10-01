package ru.yandex.practicum.telemetry.hadlers.snapshot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.enums.ConditionType;
import ru.yandex.practicum.kafka.telemetry.event.ClimateSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorStateAvro;
import ru.yandex.practicum.telemetry.service.functions.Metrics;

@Component
@RequiredArgsConstructor
public class ClimateSensorEventHandler implements SnapshotHandler {
    @Override
    public Class<?> getSensorDataClass() {
        return SensorClasses.CLIMATE_SENSOR.getSensorClass();
    }

    @Override
    public int handle(ConditionType conditionType, SensorStateAvro sensorsState) {
        ClimateSensorAvro data = (ClimateSensorAvro) sensorsState.getData();
        return Metrics.CLIMATE_SENSORS_METRICS.get(conditionType).apply(data);
    }
}

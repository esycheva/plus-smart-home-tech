package ru.yandex.practicum.telemetry.hadlers.snapshot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.enums.ConditionType;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorStateAvro;
import ru.yandex.practicum.telemetry.service.functions.Metrics;

@Component
@RequiredArgsConstructor
public class LightSensorEventHandler implements SnapshotHandler {
    @Override
    public Class<?> getSensorDataClass() {
        return SensorClasses.LIGHT_SENSOR.getSensorClass();
    }

    @Override
    public int handle(ConditionType conditionType, SensorStateAvro sensorsState) {
        LightSensorAvro data = (LightSensorAvro) sensorsState.getData();
        return Metrics.LIGHT_SENSORS_METRICS.get(conditionType).apply(data);
    }
}

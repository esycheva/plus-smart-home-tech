package ru.yandex.practicum.telemetry.hadlers.snapshot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.enums.ConditionType;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorStateAvro;
import ru.yandex.practicum.telemetry.service.functions.Metrics;

@Component
@RequiredArgsConstructor
public class MotionSensorEventHandler implements SnapshotHandler {
    @Override
    public Class<?> getSensorDataClass() {
        return SensorClasses.MOTION_SENSOR.getSensorClass();
    }

    @Override
    public int handle(ConditionType conditionType, SensorStateAvro sensorsState) {
        MotionSensorAvro data = (MotionSensorAvro) sensorsState.getData();

        if (Metrics.MOTION_SENSORS_METRICS.get(conditionType).apply(data)) {
            return 1;
        } else {
            return 0;
        }
    }
}

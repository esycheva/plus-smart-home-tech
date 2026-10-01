package ru.yandex.practicum.telemetry.hadlers.snapshot;

import ru.yandex.practicum.enums.ConditionType;
import ru.yandex.practicum.kafka.telemetry.event.SensorStateAvro;

public interface SnapshotHandler {
    Class<?> getSensorDataClass();

    int handle(ConditionType conditionType, SensorStateAvro state);
}

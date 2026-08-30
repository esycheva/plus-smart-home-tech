package ru.yandex.practicum.telemetry.service;

import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;

public interface SnapshotProcessorService {
    void processSnapshot(SensorsSnapshotAvro snapshot);
}

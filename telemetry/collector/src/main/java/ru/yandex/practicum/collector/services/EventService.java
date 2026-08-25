package ru.yandex.practicum.collector.services;

import ru.yandex.practicum.collector.hubs.HubEvent;
import ru.yandex.practicum.collector.sensors.SensorEvent;

public interface EventService {
    void collectSensorEvent(SensorEvent sensorEvent);

    void collectHubEvent(HubEvent hubEvent);
}

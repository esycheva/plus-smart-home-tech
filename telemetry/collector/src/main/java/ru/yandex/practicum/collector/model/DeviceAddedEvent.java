package ru.yandex.practicum.collector.model;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;

@Getter
@Setter
public class DeviceAddedEvent extends HubEvent {
    private DeviceType deviceType;

    @Override
    public HubEventType getType(){
        return HubEventType.DEVICE_ADDED;
    };
}

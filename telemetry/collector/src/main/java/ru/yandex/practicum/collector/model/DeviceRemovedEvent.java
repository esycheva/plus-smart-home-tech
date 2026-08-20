package ru.yandex.practicum.collector.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceRemovedEvent extends HubEvent {
    @Override
    public HubEventType getType(){
        return HubEventType.DEVICE_REMOVED_EVENT;
    };
}

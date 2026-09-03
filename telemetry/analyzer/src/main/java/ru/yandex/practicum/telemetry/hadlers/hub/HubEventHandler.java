package ru.yandex.practicum.telemetry.hadlers.hub;

public interface HubEventHandler {
    Class<?> getPayloadClass();

    void handle(Object hubEvent);
}

package ru.yandex.practicum.telemetry.hadlers.hub;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioAddedEventAvro;
import ru.yandex.practicum.telemetry.service.DataProcessorService;

@Component
@RequiredArgsConstructor
public class ScenarioAddedEventHandler implements HubEventHandler {
    private final DataProcessorService dataProcessorService;

    @Override
    public Class<?> getPayloadClass() {
        return HubEventClasses.ADD_SCENARIO.getEventClass();
    }


    @Override
    public void handle(Object hubEvent) {
        HubEventAvro event = (HubEventAvro) hubEvent;
        ScenarioAddedEventAvro payload = (ScenarioAddedEventAvro) event.getPayload();
        dataProcessorService.addScenario(event.getHubId(), payload);
    }
}
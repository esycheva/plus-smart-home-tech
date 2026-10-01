package ru.yandex.practicum.collector.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.collector.hubs.HubEvent;
import ru.yandex.practicum.collector.sensors.SensorEvent;
import ru.yandex.practicum.collector.services.KafkaSenderService;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class EventsController {
    private final KafkaSenderService eventService;

    private static final Logger log = LoggerFactory.getLogger(EventsController.class);

    @PostMapping("/sensors")
    @ResponseStatus(HttpStatus.CREATED)
    public void collectSensorEvent(@Valid @RequestBody SensorEvent event) {
        log.info("Произошло событие датчика {}.", event.toString());
        eventService.collectSensorEvent(event);
    }

    @PostMapping("/hubs")
    @ResponseStatus(HttpStatus.CREATED)
    public void collectHubEvent(@Valid @RequestBody HubEvent event) {
        eventService.collectHubEvent(event);
    }
}

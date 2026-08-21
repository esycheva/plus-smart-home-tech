package ru.yandex.practicum.collector.mapper;
import ru.yandex.practicum.kafka.telemetry.event.*;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.collector.model.*;

@Component
public class EventMapper {
    public SensorEventAvro toAvro(SensorEvent event) {
        return SensorEventAvro.newBuilder()
                .setId(event.getId())
                .setHubId(event.getHubId())
                .setTimestamp(event.getTimestamp())
                .setPayload(toSensorPayload(event))
                .build();
    }

    private Object toSensorPayload(SensorEvent event) {
        return switch (event.getType()) {
            case CLIMATE_SENSOR_EVENT ->
                    mapClimateEvent((ClimateSensorEvent) event);

            case LIGHT_SENSOR_EVENT ->
                    mapLightEvent((LightSensorEvent) event);

            case MOTION_SENSOR_EVENT ->
                    mapMotionEvent((MotionSensorEvent) event);

            case SWITCH_SENSOR_EVENT ->
                    mapSwitchEvent((SwitchSensorEvent) event);

            case TEMPERATURE_SENSOR_EVENT ->
                    mapTemperatureEvent((TemperatureSensorEvent) event);
        };
    }



    private ClimateSensorAvro mapClimateEvent(ClimateSensorEvent event) {
        return ClimateSensorAvro.newBuilder()
                .setTemperatureC(event.getTemperatureC())
                .setHumidity(event.getHumidity())
                .setCo2Level(event.getCo2Level())
                .build();
    }

    private LightSensorAvro mapLightEvent(LightSensorEvent event) {
        return LightSensorAvro.newBuilder()
                .setLinkQuality(event.getLinkQuality())
                .setLuminosity(event.getLuminosity())
                .build();
    }

    private MotionSensorAvro mapMotionEvent(MotionSensorEvent event) {
        return MotionSensorAvro.newBuilder()
                .setLinkQuality(event.getLinkQuality())
                .setMotion(event.isMotion())
                .setVoltage(event.getVoltage())
                .build();
    }

    private SwitchSensorAvro mapSwitchEvent(SwitchSensorEvent event) {
        return SwitchSensorAvro.newBuilder()
                .setState(event.isState())
                .build();
    }

    private TemperatureSensorAvro mapTemperatureEvent(
            TemperatureSensorEvent event
    ) {
        return TemperatureSensorAvro.newBuilder()
                .setTemperatureC(event.getTemperatureC())
                .setTemperatureF(event.getTemperatureF())
                .build();
    }

    public HubEventAvro toAvro(HubEvent event) {
        return HubEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setTimestamp(event.getTimestamp())
                .setPayload(toHubPayload(event))
                .build();
    }

    private Object toHubPayload(HubEvent event) {
        return switch (event.getType()) {
            case DEVICE_ADDED ->
                    mapDeviceAdded((DeviceAddedEvent) event);

            case DEVICE_REMOVED ->
                    mapDeviceRemoved((DeviceRemovedEvent) event);

            case SCENARIO_ADDED ->
                    mapScenarioAdded((ScenarioAddedEvent) event);

            case SCENARIO_REMOVED ->
                    mapScenarioRemoved((ScenarioRemovedEvent) event);
        };
    }

    private DeviceAddedEventAvro mapDeviceAdded(DeviceAddedEvent event) {
        return DeviceAddedEventAvro.newBuilder()
                .setId(event.getId())
                .setType(DeviceTypeAvro.valueOf(event.getType().name()))
                .build();
    }

    private DeviceRemovedEventAvro mapDeviceRemoved(DeviceRemovedEvent event) {
        return DeviceRemovedEventAvro.newBuilder()
                .setId(event.getId())
                .build();
    }

    private ScenarioAddedEventAvro mapScenarioAdded(ScenarioAddedEvent event) {
        return ScenarioAddedEventAvro.newBuilder()
                .setName(event.getName())
                .setConditions(
                        event.getConditions().stream()
                                .map(this::mapCondition)
                                .toList()
                )
                .setActions(
                        event.getActions().stream()
                                .map(this::mapAction)
                                .toList()
                )
                .build();
    }

    private ScenarioConditionAvro mapCondition(ScenarioCondition condition) {
        return ScenarioConditionAvro.newBuilder()
                .setSensorId(condition.getSensorId())
                .setType(ConditionTypeAvro.valueOf(condition.getType().name()))
                .setOperation(
                        ConditionOperationAvro.valueOf(
                                condition.getOperation().name()
                        )
                )
                .setValue(condition.getValue())
                .build();
    }

    private DeviceActionAvro mapAction(DeviceAction action) {
        return DeviceActionAvro.newBuilder()
                .setSensorId(action.getSensorId())
                .setType(ActionTypeAvro.valueOf(action.getType().name()))
                .setValue(action.getValue())
                .build();
    }

    private ScenarioRemovedEventAvro mapScenarioRemoved(
            ScenarioRemovedEvent event
    ) {
        return ScenarioRemovedEventAvro.newBuilder()
                .setName(event.getName())
                .build();
    }
}
package ru.yandex.practicum.collector.services;

import net.devh.boot.grpc.server.service.GrpcService;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.grpc.telemetry.collector.CollectorControllerGrpc;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import ru.yandex.practicum.collector.hubs.HubEvent;
import ru.yandex.practicum.collector.mapper.HubMapper;
import ru.yandex.practicum.collector.mapper.SensorMapper;
import ru.yandex.practicum.collector.sensors.SensorEvent;
import ru.yandex.practicum.grpc.telemetry.event.*;

@GrpcService
@RequiredArgsConstructor
public class EventServiceRpc extends CollectorControllerGrpc.CollectorControllerImplBase {
    private final KafkaSenderService eventService;

    @Override
    public void collectSensorEvent(SensorEventProto request, StreamObserver<Empty> responseObserver) {
        try {
            SensorEvent sensorEvent = SensorMapper.toSensorEvent(request);
            eventService.collectSensorEvent(sensorEvent);

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(
                    Status.INTERNAL
                            .withDescription(e.getLocalizedMessage())
                            .withCause(e)
            ));
        }
    }

    @Override
    public void collectHubEvent(HubEventProto request, StreamObserver<Empty> responseObserver) {
        try {
            HubEvent hubEvent = HubMapper.toHubEvent(request);
            eventService.collectHubEvent(hubEvent);

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(
                    Status.INTERNAL
                            .withDescription(e.getLocalizedMessage())
                            .withCause(e)
            ));
        }
    }

}

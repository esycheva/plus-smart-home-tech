package ru.yandex.practicum.telemetry.config;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import ru.practicum.telemetry.SensorEventDeserializer;
import ru.practicum.telemetry.TelemetryAvroSerializer;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaClientConfiguration {
    @Bean
    public Producer<String, SpecificRecordBase> kafkaProducer(KafkaProperties kafkaProperties) {
        return new KafkaProducer<>(kafkaProperties.buildProducerProperties(null));
    }

    @Bean
    public Consumer<String, SpecificRecordBase> kafkaConsumer(KafkaProperties kafkaProperties) {
        return new KafkaConsumer<>(kafkaProperties.buildConsumerProperties(null));
    }
}

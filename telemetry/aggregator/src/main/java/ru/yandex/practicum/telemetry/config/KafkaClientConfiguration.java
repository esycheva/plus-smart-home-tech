package ru.yandex.practicum.telemetry.config;

import java.util.Properties;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaClientConfiguration {

    @Bean(destroyMethod = "close")
    public Producer<String, SpecificRecordBase> kafkaProducer(
            @Value("${kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${kafka.producer.client-id:aggregator-producer}") String clientId,
            @Value("${kafka.producer.value-serializer}") String valueSerializer
    ) {
        Properties properties = new Properties();

        properties.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers
        );
        properties.put(
                ProducerConfig.CLIENT_ID_CONFIG,
                clientId
        );
        properties.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );
        properties.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                valueSerializer
        );

        return new KafkaProducer<>(properties);
    }

    @Bean(destroyMethod = "close")
    public Consumer<String, SpecificRecordBase> kafkaConsumer(
            @Value("${kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${kafka.consumer.client-id:aggregator-consumer}") String clientId,
            @Value("${kafka.consumer.group-id}") String groupId,
            @Value("${kafka.consumer.value-deserializer}") String valueDeserializer
    ) {
        Properties properties = new Properties();

        properties.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers
        );
        properties.put(
                ConsumerConfig.CLIENT_ID_CONFIG,
                clientId
        );
        properties.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId
        );
        properties.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );
        properties.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                valueDeserializer
        );

        return new KafkaConsumer<>(properties);
    }
}
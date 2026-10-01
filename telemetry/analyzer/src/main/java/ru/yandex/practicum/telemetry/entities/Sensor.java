package ru.yandex.practicum.telemetry.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sensors", schema = "public")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    @Id
    private String id;

    private String hubId;
}

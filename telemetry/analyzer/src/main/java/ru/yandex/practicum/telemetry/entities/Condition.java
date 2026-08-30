package ru.yandex.practicum.telemetry.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.yandex.practicum.enums.ConditionOperation;
import ru.yandex.practicum.enums.ConditionType;

@Entity
@Table(name = "conditions", schema = "public")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ConditionType type;

    @Enumerated(EnumType.STRING)
    private ConditionOperation operation;

    private Integer value;
}

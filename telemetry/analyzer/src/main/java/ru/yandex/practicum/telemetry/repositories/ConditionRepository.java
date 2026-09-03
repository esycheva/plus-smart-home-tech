package ru.yandex.practicum.telemetry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.telemetry.entities.Condition;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}

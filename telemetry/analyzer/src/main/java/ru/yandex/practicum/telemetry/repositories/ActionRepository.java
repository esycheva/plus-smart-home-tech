package ru.yandex.practicum.telemetry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.telemetry.entities.Action;

public interface ActionRepository extends JpaRepository<Action, Long> {
}

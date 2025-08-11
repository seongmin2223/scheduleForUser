package org.example.scheduleforuser.repository;

import org.example.scheduleforuser.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}

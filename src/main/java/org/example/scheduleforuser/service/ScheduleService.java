package org.example.scheduleforuser.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleforuser.dto.ScheduleRequestDto;
import org.example.scheduleforuser.dto.ScheduleResponseDto;
import org.example.scheduleforuser.entity.Schedule;
import org.example.scheduleforuser.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto);
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAllSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleResponseDtos.add(new ScheduleResponseDto(schedule));
        }//전체돌면서 스케줄리스트를 가져오는부분
        return scheduleResponseDtos;
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findOneSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 ID의 일정이 존재하지 않습니다: " + id)
        );//개별아이디를 검증하는 부분
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(ScheduleRequestDto scheduleRequestDto, Long id){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 ID의 일정이 존재하지 않습니다." + id)
        );
        if (!schedule.getPassword().equalsIgnoreCase(scheduleRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀려요");
        }//비밀번호 일치를 검증하는부분
        schedule.update(scheduleRequestDto);
        return new  ScheduleResponseDto(schedule);
    }

    @Transactional
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("\"해당 ID의 일정이 존재하지 않습니다:" + id)
        );

        scheduleRepository.delete(schedule);
    }
}

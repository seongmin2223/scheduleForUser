package org.example.scheduleforuser.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleforuser.dto.ScheduleRequestDto;
import org.example.scheduleforuser.dto.ScheduleResponseDto;
import org.example.scheduleforuser.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/createSchedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.save(scheduleRequestDto);
    }

    @GetMapping("/findAllSchedule")
    public List<ScheduleResponseDto> findAllSchedule(@RequestParam(required = false) Long id) {
        return scheduleService.findAllSchedule();
    }

    @GetMapping("/findOneSchedule/{id}")
    public ScheduleResponseDto findOneSchedule(@PathVariable Long id) {
        return scheduleService.findOneSchedule(id);
    }

    @PutMapping("/updateSchedule/{id}")
    public ScheduleResponseDto updateSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto, @PathVariable Long id) {
        return scheduleService.updateSchedule(scheduleRequestDto, id);
    }

    @DeleteMapping("/deleteSchedule/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return "삭제성공";
    }



}

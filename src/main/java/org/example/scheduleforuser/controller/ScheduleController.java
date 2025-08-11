package org.example.scheduleforuser.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleforuser.service.ScheduleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;



}

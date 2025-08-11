package org.example.scheduleforuser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    private final String title;
    private final String content;
    private final String author;
    private final String password;
}
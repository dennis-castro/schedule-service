package com.example.scheduleservice.api.mapper;


import com.example.scheduleservice.api.model.request.ScheduleRequest;
import com.example.scheduleservice.api.model.response.ScheduleResponse;
import com.example.scheduleservice.domain.entity.Schedule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper {

    @Autowired
    private ModelMapper mapper;

    public Schedule toSchedule(ScheduleRequest scheduleRequest) {
        return mapper.map(scheduleRequest, Schedule.class);
    }

    public ScheduleResponse toScheduleResponse(Schedule schedule){
        return mapper.map(schedule,ScheduleResponse.class);
    }

    public List<ScheduleResponse> toListScheduleResponse(List<Schedule> schedules){
        return schedules.stream()
                .map(schedule -> mapper.map(schedule,ScheduleResponse.class))
                .collect(Collectors.toList());
    }


}

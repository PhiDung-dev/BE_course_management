package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.ScheduleCreateRequest;
import com.example.BE_course_management.dto.request.ScheduleUpdateRequest;
import com.example.BE_course_management.dto.response.ScheduleResponse;
import com.example.BE_course_management.entity.Schedule;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.ScheduleMapper;
import com.example.BE_course_management.repository.ScheduleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleService {
    ScheduleRepository scheduleRepository;
    ScheduleMapper scheduleMapper;


    public ScheduleResponse createSchedule(ScheduleCreateRequest request)
    {
        boolean roomConflict = scheduleRepository.existsConflictByClassRoom(
                request.getClassRoomId(),
                request.getDayOfWeek(),
                request.getStartDate(),
                request.getEndDate(),
                request.getStartTime(),
                request.getEndTime()
        );
        if (roomConflict) {
            throw new AppException(ErrorCode.CLASSROOM_ALREADY_OCCUPIED);
        }
        boolean teacherConflict = scheduleRepository.existsConflictByTeacher(
                request.getTeacherId(),
                request.getDayOfWeek(),
                request.getStartDate(),
                request.getEndDate(),
                request.getStartTime(),
                request.getEndTime()
        );
        if (teacherConflict) {
            throw new AppException(ErrorCode.TEACHER_ALREADY_BUSY);
        }

        Schedule schedule = scheduleMapper.toSchedule(request);
        Schedule saveSchedule = scheduleRepository.save(schedule);

        return scheduleMapper.toScheduleResponse(saveSchedule);
    }

    public ScheduleResponse updateSchedule(String id, ScheduleUpdateRequest request)
    {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.SCHEDULE_NOT_FOUND));
        scheduleMapper.updateSchedule(schedule,request);
        scheduleRepository.save(schedule);
        return scheduleMapper.toScheduleResponse(schedule);
    }

    public List<ScheduleResponse> readSchedules()
    {
        return scheduleMapper.toScheduleList(scheduleRepository.findAll());
    }
    public ScheduleResponse readSchedule(String id)
    {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_FOUND));
        return scheduleMapper.toScheduleResponse(schedule);
    }

    public void deleteSchedule(String id)
    {
        if(!scheduleRepository.existsById(id)){
            throw new AppException(ErrorCode.SCHEDULE_NOT_FOUND);
        }
        scheduleRepository.deleteById(id);
    }
}

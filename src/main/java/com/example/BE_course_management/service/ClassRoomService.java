package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.ClassRoomCreateRequest;
import com.example.BE_course_management.dto.request.ClassRoomUpdateRequest;
import com.example.BE_course_management.dto.response.ClassRoomResponse;
import com.example.BE_course_management.entity.ClassRoom;
import com.example.BE_course_management.entity.ClassRoomStatus;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.ClassRoomMapper;
import com.example.BE_course_management.repository.ClassRoomRepository;
import com.example.BE_course_management.repository.ScheduleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClassRoomService {

    ClassRoomMapper classRoomMapper;
    ClassRoomRepository classRoomRepository;
    ScheduleRepository scheduleRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public ClassRoomResponse createClassRoom(ClassRoomCreateRequest request) {
        if(classRoomRepository.existsByRoomNumber(request.getRoomNumber())) {
            throw new AppException(ErrorCode.CLASSROOM_EXISTED);
        }
        ClassRoom classRoom = classRoomMapper.toClassRoom(request);
        classRoomRepository.save(classRoom);
        ClassRoomResponse response = classRoomMapper.toClassRoomResponse(classRoom);
        response.setStatus(getRoomStatus(response.getId()));
        return response;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<ClassRoomResponse> readClassRooms() {
        List<ClassRoom> classRooms = classRoomRepository.findAll();
        List<ClassRoomResponse> responses = new ArrayList<>();
        for (ClassRoom classRoom : classRooms) {
            ClassRoomResponse response = classRoomMapper.toClassRoomResponse(classRoom);
            response.setStatus(getRoomStatus(classRoom.getId()));
            responses.add(response);
        }

        return responses;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ClassRoomResponse readClassRoom(String id) {
        ClassRoom classRoom = classRoomRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.CLASSROOM_NOT_FOUND));
        ClassRoomResponse classRoomResponse = classRoomMapper.toClassRoomResponse(classRoom);
        classRoomResponse.setStatus(getRoomStatus(classRoomResponse.getId()));
        return classRoomResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ClassRoomResponse updateClassRoom(String id, ClassRoomUpdateRequest request) {
        ClassRoom classRoom = classRoomRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.CLASSROOM_NOT_FOUND));
        classRoomMapper.updateClassRoom(classRoom,request);
        classRoomRepository.save(classRoom);
        ClassRoomResponse classRoomResponse = classRoomMapper.toClassRoomResponse(classRoom);
        classRoomResponse.setStatus(getRoomStatus(classRoomResponse.getId()));
        return classRoomResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteClassRoom(String id) {
        if(!classRoomRepository.existsById(id)) {
            throw new AppException(ErrorCode.CLASSROOM_NOT_FOUND);
        }
        classRoomRepository.deleteById(id);
    }

    private ClassRoomStatus getRoomStatus(String classRoomId) {
        boolean using = scheduleRepository
                .existsByClassRoomIdAndDayOfWeekAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        classRoomId,
                        LocalDate.now().getDayOfWeek(),
                        LocalDate.now(),
                        LocalDate.now(),
                        LocalTime.now(),
                        LocalTime.now()
                );
        return using ? ClassRoomStatus.USING : ClassRoomStatus.AVAILABLE;
    }

}

package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.ApiResponse;
import com.example.BE_course_management.dto.request.ClassRoomCreateRequest;
import com.example.BE_course_management.dto.request.ClassRoomUpdateRequest;
import com.example.BE_course_management.dto.response.ClassRoomResponse;
import com.example.BE_course_management.entity.ClassRoom;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.ClassRoomMapper;
import com.example.BE_course_management.repository.ClassRoomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ClassRoomService {

    ClassRoomMapper classRoomMapper;
    ClassRoomRepository classRoomRepository;

    public ClassRoomResponse createClassRoom(ClassRoomCreateRequest request)
    {
        ClassRoom classRoom = classRoomMapper.toClassRoom(request);
        ClassRoom saveClassRoom = classRoomRepository.save(classRoom);

        return classRoomMapper.toClassRoomResponse(saveClassRoom);
    }

    public ClassRoomResponse updateClassRoom(String id ,ClassRoomUpdateRequest request)
    {
        ClassRoom classRoom = classRoomRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.CLASSROOM_NOT_FOUND));
                classRoomMapper.updateClassRoom(classRoom,request);
                ClassRoom saveClassRoom = classRoomRepository.save(classRoom);

            return classRoomMapper.toClassRoomResponse(saveClassRoom);
    }

    public ClassRoomResponse readClassRoom(String id)
    {
        ClassRoom classRoom = classRoomRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.CLASSROOM_NOT_FOUND));

        return classRoomMapper.toClassRoomResponse(classRoom);

    }
    public List<ClassRoomResponse> readClassRooms()
    {
        List<ClassRoom> classList = classRoomRepository.findAll();
        return classRoomMapper.toClassRoomResponseList(classList);
    }

    public void deleteClassRoom(String id)
    {
        ClassRoom classRoom = classRoomRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.CLASSROOM_NOT_FOUND));
        classRoomRepository.delete(classRoom);
    }


}

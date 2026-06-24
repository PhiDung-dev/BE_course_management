package com.example.BE_course_management.controller;

import com.example.BE_course_management.dto.ApiResponse;
import com.example.BE_course_management.dto.request.ClassRoomCreateRequest;
import com.example.BE_course_management.dto.request.ClassRoomUpdateRequest;
import com.example.BE_course_management.dto.response.ClassRoomResponse;
import com.example.BE_course_management.service.ClassRoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/classRooms")
public class ClassRoomController {
    ClassRoomService classRoomService;

    @PostMapping
    public ApiResponse<ClassRoomResponse> createClassRoom(@RequestBody ClassRoomCreateRequest request)
    {
        return ApiResponse.<ClassRoomResponse>builder()
                .result(classRoomService.createClassRoom(request))
                .build();
    }
    @PutMapping("/{classRoomId}")
    public ApiResponse<ClassRoomResponse> updateClassRoom(@PathVariable("classRoomId") String classRoomId, @RequestBody ClassRoomUpdateRequest request)
    {
        return ApiResponse.<ClassRoomResponse>builder()
                .result(classRoomService.updateClassRoom(classRoomId,request))
                .build();
    }

    @GetMapping("/{classRoomId}")
    public ApiResponse<ClassRoomResponse> readClassRoom(@PathVariable("classRoomId") String classRoomId)
    {
        return ApiResponse.<ClassRoomResponse>builder()
                .result(classRoomService.readClassRoom(classRoomId))
                .build();
    }
    @GetMapping
    public ApiResponse<List<ClassRoomResponse>> readClassRooms()
    {
        return ApiResponse.<List<ClassRoomResponse>>builder()
                .result(classRoomService.readClassRooms())
                .build();
    }

    @DeleteMapping("/{classRoomId}")
    public ApiResponse<String> deleteClassRooms(@PathVariable("classRoomId") String classRoomId)
    {

        classRoomService.deleteClassRoom(classRoomId);
        return ApiResponse.<String>builder()
                .code(1000)
                .message("Class Room has id = " + classRoomId + "deleted succesfully")
                .build();
    }
}

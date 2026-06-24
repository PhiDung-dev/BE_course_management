package com.example.BE_course_management.mapper;

import com.example.BE_course_management.dto.request.CourseCreateRequest;
import com.example.BE_course_management.dto.request.CourseUpdateRequest;
import com.example.BE_course_management.dto.response.CourseResponse;
import com.example.BE_course_management.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toCourse(CourseCreateRequest request);
    CourseResponse toCourseResponse(Course course);
    List<CourseResponse> toCourseResponseList(List<Course> courses);
    void  updateCourse(@MappingTarget Course course, CourseUpdateRequest request);

}

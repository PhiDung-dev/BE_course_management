package com.example.BE_course_management.mapper;

import com.example.BE_course_management.dto.request.CourseCreateRequest;
import com.example.BE_course_management.dto.request.CourseUpdateRequest;
import com.example.BE_course_management.dto.response.CourseResponse;
import com.example.BE_course_management.entity.Course;
import com.example.BE_course_management.entity.CourseImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "courseImages", ignore = true)
    Course toCourse(CourseCreateRequest request);

    @Mapping(target = "imgs", source = "courseImages")
    CourseResponse toCourseResponse(Course course);

    List<CourseResponse> toCourseResponseList(List<Course> courses);

    @Mapping(target = "courseImages", ignore = true)
    void updateCourse(@MappingTarget Course course, CourseUpdateRequest request);

    default String map(CourseImage courseImage) {
        return courseImage.getUrl();
    }

}

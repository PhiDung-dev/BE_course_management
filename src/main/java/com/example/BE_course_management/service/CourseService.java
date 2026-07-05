package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.CourseCreateRequest;
import com.example.BE_course_management.dto.request.CourseUpdateRequest;
import com.example.BE_course_management.dto.response.CourseResponse;
import com.example.BE_course_management.entity.Course;
import com.example.BE_course_management.entity.CourseDocument;
import com.example.BE_course_management.entity.CourseImage;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.CourseMapper;
import com.example.BE_course_management.repository.CourseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService {

    CourseRepository courseRepository;
    CourseMapper courseMapper;

     public CourseResponse createCourse(CourseCreateRequest request) {
         if(courseRepository.existsByTitle(request.getTitle().trim())) {
             throw new AppException(ErrorCode.COURSE_EXISTED);
         }
         Course course = courseMapper.toCourse(request);
         List<CourseImage> courseImages = request.getImages().stream().map(img -> {
             return CourseImage.builder()
                     .url(img)
                     .course(course)
                     .build();
         }).toList();
         List<CourseDocument> courseDocuments = request.getDocuments().stream().map(doc -> {
             return CourseDocument.builder()
                     .title(doc.getTitle())
                     .url(doc.getUrl())
                     .course(course)
                     .build();
         }).toList();
         course.setCourseImages(courseImages);
         course.setCourseDocuments(courseDocuments);
         return courseMapper.toCourseResponse(courseRepository.save(course));
     }

    public CourseResponse readCourse(String id) {
        Course course = courseRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.COURSE_NOT_FOUND));
        return courseMapper.toCourseResponse(course);
    }

    public List<CourseResponse> readCourses() {
        return courseMapper.toCourseResponseList(courseRepository.findAll());
    }

     public CourseResponse updateCourse(String id, CourseUpdateRequest request) {
         Course course = courseRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.COURSE_NOT_FOUND));
         courseMapper.updateCourse(course,request);
         course.getCourseImages().clear();
         request.getImages().forEach(img-> {
             CourseImage courseImage = CourseImage.builder()
                     .url(img)
                     .course(course)
                     .build();
             course.getCourseImages().add(courseImage);
         });
         course.getCourseDocuments().clear();
         request.getDocuments().forEach(doc-> {
             CourseDocument courseDocument = CourseDocument.builder()
                     .title(doc.getTitle())
                     .url(doc.getUrl())
                     .course(course)
                     .build();
             course.getCourseDocuments().add(courseDocument);
         });
         return courseMapper.toCourseResponse(courseRepository.save(course));
     }

     public void deleteCourse(String id) {
         if(!courseRepository.existsById(id)) {
             throw new AppException(ErrorCode.COURSE_NOT_FOUND);
         }
         courseRepository.deleteById(id);
     }

}

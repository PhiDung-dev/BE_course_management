package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.BookingCreateRequest;
import com.example.BE_course_management.dto.request.BookingUpdateRequest;
import com.example.BE_course_management.dto.response.BookingResponse;
import com.example.BE_course_management.entity.Booking;
import com.example.BE_course_management.entity.BookingStatus;
import com.example.BE_course_management.entity.Course;
import com.example.BE_course_management.entity.User;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.BookingMapper;
import com.example.BE_course_management.repository.BookingRepository;
import com.example.BE_course_management.repository.CourseRepository;
import com.example.BE_course_management.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {

    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    CourseRepository courseRepository;
    UserRepository userRepository;

    public BookingResponse createBooking(BookingCreateRequest request) {
        Booking booking = bookingMapper.toBooking(request);
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(()->new AppException(ErrorCode.COURSE_NOT_FOUND));
        User user = userRepository.findById(request.getUserId()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        booking.setCourse(course);
        booking.setUser(user);
        booking.setStatus(BookingStatus.PENDING);
        booking.setTotalPrice(course.getPrice());
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    public List<BookingResponse> readBookings() {
        return bookingMapper.toBookingResponseList(bookingRepository.findAll());
    }

    public BookingResponse readBooking(String id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.BOOKING_NOT_FOUND));
        return bookingMapper.toBookingResponse(booking);
    }

    public BookingResponse updateBooking(String id, BookingUpdateRequest request) {
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.BOOKING_NOT_FOUND));
        bookingMapper.updateBooking(booking, request);
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    public void deleteBooking(String id) {
        if(!bookingRepository.existsById(id)) {
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        bookingRepository.deleteById(id);
    }

}

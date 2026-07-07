package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.BookingCreateRequest;
import com.example.BE_course_management.dto.request.BookingUpdateRequest;
import com.example.BE_course_management.dto.response.BookingResponse;
import com.example.BE_course_management.entity.*;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.BookingMapper;
import com.example.BE_course_management.repository.BookingRepository;
import com.example.BE_course_management.repository.ScheduleRepository;
import com.example.BE_course_management.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {

    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    ScheduleRepository scheduleRepository;
    UserRepository userRepository;

    @PreAuthorize("@securityService.isOwnerUser(#request.userId, authentication.name)")
    public BookingResponse createBooking(BookingCreateRequest request) {
        if(bookingRepository.existsByUserIdAndScheduleId(request.getUserId(), request.getScheduleId())) {
            throw new AppException(ErrorCode.BOOKING_EXISTED);
        }
        Schedule schedule = scheduleRepository.findById(request.getScheduleId()).orElseThrow(()->new AppException(ErrorCode.SCHEDULE_NOT_FOUND));
        if(schedule.getSlot()<=0) {
            throw new AppException(ErrorCode.SLOT_UNAVAILABLE);
        }
        User user = userRepository.findById(request.getUserId()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        Booking booking = bookingMapper.toBooking(request);
        booking.setSchedule(schedule);
        booking.setUser(user);
        booking.setStatus(BookingStatus.PENDING);
        booking.setTotalPrice(schedule.getCourse().getPrice());
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<BookingResponse> readBookings() {
        return bookingMapper.toBookingResponseList(bookingRepository.findAll());
    }

    @PreAuthorize("@securityService.isOwnerUser(#userId, authentication.name)")
    public List<BookingResponse> readBookingsByUserId(String userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookingMapper.toBookingResponseList(bookings);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<BookingResponse> readBookingsByStatus(String status) {
        List<Booking> bookings = bookingRepository.findByStatus(status);
        return bookingMapper.toBookingResponseList(bookings);
    }

    @PreAuthorize("@securityService.isOwnerBooking(#id, authentication.name)")
    public BookingResponse readBooking(String id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.BOOKING_NOT_FOUND));
        return bookingMapper.toBookingResponse(booking);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public BookingResponse updateBooking(String id, BookingUpdateRequest request) {
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.BOOKING_NOT_FOUND));
        bookingMapper.updateBooking(booking, request);
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    @PreAuthorize("@securityService.isOwnerBooking(#id, authentication.name)")
    public void deleteBooking(String id) {
        if(!bookingRepository.existsById(id)) {
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        bookingRepository.deleteById(id);
    }

}

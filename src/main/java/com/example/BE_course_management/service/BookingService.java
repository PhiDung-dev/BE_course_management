package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.BookingCreateRequest;
import com.example.BE_course_management.dto.request.BookingUpdateRequest;
import com.example.BE_course_management.dto.response.BookingResponse;
import com.example.BE_course_management.entity.Booking;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.BookingMapper;
import com.example.BE_course_management.repository.BookingRepository;
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

    public BookingResponse createBooking(BookingCreateRequest request)
    {
        Booking booking = bookingMapper.toBooking(request);
        Booking saveBooking = bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(saveBooking);
    }

    public BookingResponse updateBooking(String id, BookingUpdateRequest request)
    {
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.BOOKING_NOT_FOUND));
        bookingMapper.updateBooking(booking,request);
        bookingRepository.save(booking);
        return bookingMapper.toBookingResponse(booking);
    }

    public void deleteBooking( String id)
    {
        if(!bookingRepository.existsById(id)){
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        bookingRepository.deleteById(id);
    }
    public BookingResponse readBooking(String id)
    {
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.BOOKING_NOT_FOUND));
        return bookingMapper.toBookingResponse(booking);
    }
    public List<BookingResponse> readBookings()
    {
        return bookingMapper.toBookingResponseList(bookingRepository.findAll());
    }
}

package com.example.BE_course_management.controller;

import com.example.BE_course_management.dto.ApiResponse;
import com.example.BE_course_management.dto.request.BookingCreateRequest;
import com.example.BE_course_management.dto.request.BookingUpdateRequest;
import com.example.BE_course_management.dto.response.BookingResponse;
import com.example.BE_course_management.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {
    BookingService bookingService;
    @PostMapping
    public ApiResponse<BookingResponse> createBooking(@RequestBody BookingCreateRequest request)
    {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.createBooking(request))
                .build();
    }
    @PutMapping("/{bookingId}")
    public ApiResponse<BookingResponse> updateBooking(@PathVariable("bookingId") String id, @RequestBody BookingUpdateRequest request)
    {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.updateBooking(id,request))
                .build();
    }
    @GetMapping
    public ApiResponse<List<BookingResponse>> readBookings()
    {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.readBookings())
                .build();
    }
    @GetMapping("/{bookingId}")
    public ApiResponse<BookingResponse> readBooking(@PathVariable("bookingId") String id)
    {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.readBooking(id))
                .build();
    }
    @DeleteMapping("/{bookingId}")
    public ApiResponse<String> deleteBooking(@PathVariable("bookingId") String id)
    {
        return ApiResponse.<String>builder()
                .code(1000)
                .message("Booking has id = " + id + "deleted succesfully")
                .build();
    }

}

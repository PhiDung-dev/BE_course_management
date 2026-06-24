package com.example.BE_course_management.mapper;
import com.example.BE_course_management.dto.request.BookingCreateRequest;
import com.example.BE_course_management.dto.request.BookingUpdateRequest;
import com.example.BE_course_management.dto.response.BookingResponse;
import com.example.BE_course_management.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toBooking(BookingCreateRequest request);
    BookingResponse toBookingResponse(Booking booking);
    List<BookingResponse> toBookingResponseList(List<Booking> bookings);
    void updateBooking(@MappingTarget Booking booking, BookingUpdateRequest request);
}

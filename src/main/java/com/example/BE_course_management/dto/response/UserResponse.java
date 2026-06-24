package com.example.BE_course_management.dto.response;

import com.example.BE_course_management.entity.Account;
import com.example.BE_course_management.entity.Booking;
import com.example.BE_course_management.entity.Cart;
import com.example.BE_course_management.entity.Schedule;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String fullName;
    String email;
    String phoneNumber;
    String address;
    LocalDate dateOfBirth;
    AccountResponse account;
    List<Schedule> schedules;
    List<Cart> carts;
    List<Booking> bookings;
}

package com.example.BE_course_management.dto.response;

import com.example.BE_course_management.entity.BookingStatus;
import com.example.BE_course_management.entity.Course;
import com.example.BE_course_management.entity.Payment;
import com.example.BE_course_management.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    String id;
    String description;
    BookingStatus status;
    BigDecimal totalPrice;
    Course course;
    UserResponse user;
    PaymentResponse payment;
}

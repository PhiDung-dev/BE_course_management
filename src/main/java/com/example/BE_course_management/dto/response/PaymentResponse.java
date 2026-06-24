package com.example.BE_course_management.dto.response;

import com.example.BE_course_management.entity.Booking;
import com.example.BE_course_management.entity.PaymentStatus;
import com.example.BE_course_management.entity.Rating;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    String id;
    PaymentStatus status;
    BigDecimal amount;
    BookingResponse booking;
    RatingResponse rating;
}

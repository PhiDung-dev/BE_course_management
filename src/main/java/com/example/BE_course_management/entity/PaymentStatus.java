package com.example.BE_course_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {

    PENDING("Payment pending"),
    SUCCESS("Payment success"),
    FAILED("Payment failed"),
    CANCELLED("Payment cancelled");

   private final String status;

}

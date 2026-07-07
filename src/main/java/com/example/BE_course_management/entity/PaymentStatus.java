package com.example.BE_course_management.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {

    PENDING("PENDING"),
    SUCCESS("SUCCESS")
    ;

   private final String status;

}

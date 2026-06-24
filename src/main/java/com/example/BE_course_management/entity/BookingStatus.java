package com.example.BE_course_management.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookingStatus {

    PENDING("Pending confirmation"),
    CONFIRMED("Confirmed");


    private final String status;
}

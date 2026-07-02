package com.example.BE_course_management.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookingStatus {

    PENDING("PENDING"),
    CONFIRMED("CONFIRMED");

    private final String status;

}

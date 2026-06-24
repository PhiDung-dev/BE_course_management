package com.example.BE_course_management.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountStatus {
    ACTIVE("Account is active"),
    BLOCKED("Account is blocked");

    private final String status;
}

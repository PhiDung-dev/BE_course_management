package com.example.BE_course_management.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {

    ACCOUNT_EXISTED(1001, "account existed"),
    ACCOUNT_NOT_FOUND(1002, "account not found"),
    USER_EXISTED(1003, "user existed"),
    USER_NOT_FOUND(1004, "user not found"),
    COURSE_EXISTED(1005, "course existed"),
    COURSE_NOT_FOUND(1006, "course not found"),
    SCHEDULE_CONFLICT(1007, "schedule conflict"),
    SCHEDULE_NOT_FOUND(1008, "schedule not found"),
    SCHEDULE_FULL(1009, "schedule full"),
    BOOKING_EXISTED(1010, "booking existed"),
    BOOKING_NOT_FOUND(1011, "booking not found"),
    PAYMENT_EXISTED(1012, "payment existed"),
    PAYMENT_NOT_FOUND(1013, "payment not found"),
    PAYMENT_ALREADY_PROCESSED(1014,"payment already processed"),
    RATING_EXISTED(1015, "rating existed"),
    RATING_NOT_FOUND(1016, "rating not found"),
    SCORE_NOT_FOUND(1017, "score not found"),
    COURSE_ALREADY_IN_CART(1018,"course already in cart"),
    COURSE_NOT_FOUND_IN_CART(1019,"course not found in cart"),
    CLASSROOM_EXISTED(1020, "class room existed"),
    CLASSROOM_NOT_FOUND(1021,"class room not found "),
    PASSWORD_NOT_MATCH(1022, "password not match"),
    ROLE_NOT_FOUND(1023, "role not found"),
    SLOT_UNAVAILABLE(1024, "slot unavailable"),
    UNPAID(1025, "unpaid"),
    UNAUTHENTICATED(1234, "unauthenticated"),
    OTHERS_ERROR(9999, "others error"),
    ;

    final int code;
    final String message;

}

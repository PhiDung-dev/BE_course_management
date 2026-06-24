package com.example.BE_course_management.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ACCOUNT_EXISTED(1001, "account existed"),
    ACCOUNT_NOT_FOUND(1002, "account not found"),
    USER_EXISTED(1003, "user existed"),
    USER_NOT_FOUND(1004, "user not found"),
    COURSE_EXISTED(1005, "course existed"),
    COURSE_NOT_FOUND(1006, "course not found"),
    SCHEDULE_NOT_FOUND(1007, "schedule not found"),
    BOOKING_NOT_FOUND(1008, "booking not found"),
    PAYMENT_EXISTED(1009, "payment existed"),
    PAYMENT_NOT_FOUND(1010, "payment not found"),
    PAYMENT_ALREADY_PROCESSED(1300,"payment already processed"),
    RATING_EXISTED(1011, "rating existed"),
    RATING_NOT_FOUND(1012, "rating not found"),
    NOT_ENOUGH_SLOT(1015, "not enough slot"),
    OTHERS_ERROR(9999, "others error"),
    COURSE_ALREADY_IN_CART(1120,"course already in cart"),
    COURSE_NOT_FOUND_IN_CART(1121,"course not found in cart"),
    TEACHER_ALREADY_BUSY(1500,"teacher already busy"),
    CLASSROOM_ALREADY_OCCUPIED(1511,"class room already occupied"),
    CLASSROOM_NOT_FOUND(1302,"class room not found ");

    private final int code;
    private final String message;





}

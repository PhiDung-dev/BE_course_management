package com.example.BE_course_management.dto.response;

import com.example.BE_course_management.entity.ClassRoomStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassRoomResponse {

    String id;
    String roomNumber;
    ClassRoomStatus status;

}

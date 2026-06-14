package com.example.BE_course_management.dto.request;

import com.example.BE_course_management.entity.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentUpdateRequest {

    PaymentStatus status;

}

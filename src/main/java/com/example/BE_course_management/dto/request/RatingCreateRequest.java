package com.example.BE_course_management.dto.request;

import com.example.BE_course_management.entity.Payment;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingCreateRequest {

    int score;
    String comment;
    String paymentId;

}

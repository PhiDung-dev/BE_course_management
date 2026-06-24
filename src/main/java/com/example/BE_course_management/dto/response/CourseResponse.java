package com.example.BE_course_management.dto.response;

import com.example.BE_course_management.entity.Booking;
import com.example.BE_course_management.entity.Cart;
import com.example.BE_course_management.entity.CourseImage;
import com.example.BE_course_management.entity.Schedule;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {
    String id;
    String title;
    String description;
    Integer slot;
    BigDecimal price;
    List<CourseImage> courseImages;
    List<Schedule> schedules;
    List<Cart> carts;
    List<Booking> bookings;
}

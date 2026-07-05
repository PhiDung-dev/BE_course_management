package com.example.BE_course_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String fullName;
    @Column(unique = true)
    String email;
    @Column(unique = true)
    String phoneNumber;
    String address;
    LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "account_id", unique = true)
    Account account;

    @OneToMany(mappedBy = "teacher")
    List<Schedule> schedules;

    @OneToMany(mappedBy = "user")
    List<Cart> carts;

    @OneToMany(mappedBy = "user")
    List<Booking> bookings;
    
}

package com.example.BE_course_management.service;

import com.example.BE_course_management.entity.*;
import com.example.BE_course_management.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityService {

    AccountRepository accountRepository;
    UserRepository userRepository;
    CartRepository cartRepository;
    ScheduleRepository scheduleRepository;
    BookingRepository bookingRepository;
    PaymentRepository paymentRepository;
    ScoreRepository scoreRepository;

    public boolean isOwnerAccount(String id, String username) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(value -> value.getUsername().equalsIgnoreCase(username)).orElse(false);
    }

    public boolean isOwnerUser(String id, String username) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value->value.getAccount().getUsername().equalsIgnoreCase(username)).orElse(false);
    }

    public boolean isOwnerCart(String id, String username) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.map(value->value.getUser().getAccount().getUsername().equalsIgnoreCase(username)).orElse(false);
    }

    public boolean isOwnerBooking(String id, String username) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(value->value.getUser().getAccount().getUsername().equalsIgnoreCase(username)).orElse(false);
    }

    public boolean isOwnerPayment(String id, String username) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(value->value.getBooking().getUser().getAccount().getUsername().equalsIgnoreCase(username)).orElse(false);
    }

    public boolean isOwnerScore(String id, String username) {
        Optional<Score> score = scoreRepository.findById(id);
        return score.map(value-> value.getPayment().getBooking().getUser().getAccount().getUsername().equalsIgnoreCase(username)).orElse(false);
    }

    public boolean isOwnerTeacherOfScore(String id, String username) {
        Optional<Score> score = scoreRepository.findById(id);
        return score.map(value->value.getPayment().getBooking().getSchedule().getTeacher().getAccount().getUsername().equalsIgnoreCase(username)).orElse(false);
    }

    public boolean isOwnerTeacherOfSchedule(String id, String username) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.map(value->value.getTeacher().getAccount().getUsername().equalsIgnoreCase(username)).orElse(false);
    }

    public boolean isStudentOfSchedule(String id, String username) {
        return scheduleRepository.findById(id)
                .map(schedule ->
                        schedule.getBookings()
                                .stream()
                                .anyMatch(booking ->
                                        booking.getUser()
                                                .getAccount()
                                                .getUsername()
                                                .equalsIgnoreCase(username)))
                .orElse(false);
    }

}

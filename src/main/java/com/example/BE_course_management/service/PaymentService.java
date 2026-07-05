package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.PaymentCreateRequest;
import com.example.BE_course_management.dto.request.PaymentUpdateRequest;
import com.example.BE_course_management.dto.response.PaymentResponse;
import com.example.BE_course_management.entity.*;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.PaymentMapper;
import com.example.BE_course_management.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService {

    PaymentMapper paymentMapper;
    PaymentRepository paymentRepository;
    BookingRepository bookingRepository;
    ScheduleRepository scheduleRepository;
    ScoreRepository scoreRepository;

    public PaymentResponse createPayment(PaymentCreateRequest request) {
        if (paymentRepository.existsByBookingId(request.getBookingId())) {
            throw new AppException(ErrorCode.PAYMENT_EXISTED);
        }
        Booking booking = bookingRepository.findById(request.getBookingId()).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        booking.setStatus(BookingStatus.CONFIRMED);
        Payment payment = Payment.builder()
                .status(PaymentStatus.PENDING)
                .amount(booking.getTotalPrice())
                .booking(booking)
                .build();
        bookingRepository.save(booking);
        return paymentMapper.toPaymentResponse(paymentRepository.save(payment));
    }

    public List<PaymentResponse> readPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return paymentMapper.toPaymentResponseList(payments);
    }

    public PaymentResponse readPaymentByBookingId(String bookingId){
        Payment payment = paymentRepository.findByBookingId(bookingId).orElseThrow(()->new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        return paymentMapper.toPaymentResponse(payment);
    }

    public List<PaymentResponse> readPaymentsByStatus(String status) {
        List<Payment> payments = paymentRepository.findByStatus(status);
        return paymentMapper.toPaymentResponseList(payments);
    }

    public PaymentResponse readPayment(String id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        return paymentMapper.toPaymentResponse(payment);
    }

    public PaymentResponse updatePayment(String id, PaymentUpdateRequest request) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            throw new AppException(ErrorCode.PAYMENT_ALREADY_PROCESSED);
        }
        paymentMapper.updatePayment(payment, request);
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            Booking booking = payment.getBooking();
            Schedule schedule = booking.getSchedule();
            if (schedule.getSlot() <= 0) {
                throw new AppException(ErrorCode.SCHEDULE_FULL);
            }
            schedule.setSlot(schedule.getSlot() - 1);
            Score score = Score.builder()
                    .attendanceScore(null)
                    .midtermScore(null)
                    .finalScore(null)
                    .averageScore(null)
                    .classification(Classification.NOT_GRADED)
                    .payment(payment)
                    .build();
            scheduleRepository.save(schedule);
            scoreRepository.save(score);
        }
        return paymentMapper.toPaymentResponse(paymentRepository.save(payment));
    }

}

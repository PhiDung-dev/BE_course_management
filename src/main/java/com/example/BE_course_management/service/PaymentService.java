package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.PaymentCreateRequest;
import com.example.BE_course_management.dto.request.PaymentUpdateRequest;
import com.example.BE_course_management.dto.response.PaymentResponse;
import com.example.BE_course_management.entity.Booking;
import com.example.BE_course_management.entity.Payment;
import com.example.BE_course_management.entity.PaymentStatus;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.PaymentMapper;
import com.example.BE_course_management.repository.BookingRepository;
import com.example.BE_course_management.repository.PaymentRepository;
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

    public PaymentResponse createPayment(PaymentCreateRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId()).orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        Payment payment = Payment.builder()
                .status(PaymentStatus.PENDING)
                .amount(booking.getTotalPrice())
                .booking(booking)
                .build();
        return paymentMapper.toPaymentResponse(paymentRepository.save(payment));
    }

    public List<PaymentResponse> readPayments() {
        List<Payment> listPayment = paymentRepository.findAll();
        return paymentMapper.toPaymentResponseList(listPayment);
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
        return paymentMapper.toPaymentResponse(paymentRepository.save(payment));
    }

}

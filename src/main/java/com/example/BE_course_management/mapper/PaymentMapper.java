package com.example.BE_course_management.mapper;

import com.example.BE_course_management.dto.request.PaymentCreateRequest;
import com.example.BE_course_management.dto.request.PaymentUpdateRequest;
import com.example.BE_course_management.dto.response.PaymentResponse;
import com.example.BE_course_management.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toPayment (PaymentCreateRequest request);
    PaymentResponse toPaymentRespone(Payment payment);
    List<PaymentResponse> toPaymentResponseList(List<Payment> payments);
    void updatePayment(@MappingTarget Payment payment, PaymentUpdateRequest request);
}

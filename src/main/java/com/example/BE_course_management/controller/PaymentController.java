package com.example.BE_course_management.controller;

import com.example.BE_course_management.dto.ApiResponse;
import com.example.BE_course_management.dto.request.PaymentCreateRequest;
import com.example.BE_course_management.dto.request.PaymentUpdateRequest;
import com.example.BE_course_management.dto.response.PaymentResponse;
import com.example.BE_course_management.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {
    PaymentService paymentService;

    @PostMapping
    public ApiResponse<PaymentResponse> createPayment(@RequestBody PaymentCreateRequest request)
    {
        return ApiResponse.<PaymentResponse>builder()
                .result(paymentService.createPayment(request))
                .build();
    }

    @PutMapping("/{paymentId}")
    public ApiResponse<PaymentResponse> updatePayment(@PathVariable("paymentId") String paymentId, @RequestBody PaymentUpdateRequest request)
    {
        return ApiResponse.<PaymentResponse>builder()
                .result(paymentService.updatePayment(paymentId,request))
                .build();
    }
    @GetMapping
    public ApiResponse<List<PaymentResponse>> readPayments()
    {
        return ApiResponse.<List<PaymentResponse>>builder()
                .result(paymentService.readPayments())
                .build();
    }
    @GetMapping("/{paymentId}")
    public ApiResponse<PaymentResponse> readPayment(@PathVariable("paymentId") String paymentId)
    {
        return ApiResponse.<PaymentResponse>builder()
                .result(paymentService.readPayment(paymentId))
                .build();
    }
    @DeleteMapping("/{paymentId}")
    public ApiResponse<String> cancelPayment(@PathVariable("paymentId") String paymentId)
    {
        paymentService.cancelPayment(paymentId);
        return ApiResponse.<String>builder()
                .code(1000)
                .message("Payment has paymentId = "+ paymentId + "deleted succesfully")
                .build();
    }
}

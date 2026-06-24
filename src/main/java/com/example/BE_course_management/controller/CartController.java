package com.example.BE_course_management.controller;

import com.example.BE_course_management.dto.ApiResponse;
import com.example.BE_course_management.dto.request.CartCreateRequest;
import com.example.BE_course_management.dto.response.CartResponse;
import com.example.BE_course_management.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    CartService cartService;

    @PostMapping
    public ApiResponse<CartResponse> createCart(@RequestBody CartCreateRequest request)
    {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.addItemInCart(request))
                .build();
    }
    @GetMapping("/{userId}")
    public ApiResponse<List<CartResponse>> readCartByUser(@PathVariable("userId") String userId)
    {
        return ApiResponse.<List<CartResponse>>builder()
                .result(cartService.readCartsByUser(userId))
                .build();
    }
    @GetMapping("/search")
    public ApiResponse<CartResponse> searchCourseInCart(@RequestParam String userId, @RequestParam String title)
    {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.searchCourseInCart(userId,title))
                .build();
    }
    @DeleteMapping("/{cartId}")
    public ApiResponse<String> deleteCart(@PathVariable("cartId") String cartId)
    {
        cartService.deleteCart(cartId);
        return ApiResponse.<String>builder()
                .code(1000)
                .message("id = "+ cartId +"deleted succesful")
                .build();
    }
}

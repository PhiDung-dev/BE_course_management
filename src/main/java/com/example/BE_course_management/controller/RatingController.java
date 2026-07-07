package com.example.BE_course_management.controller;

import com.example.BE_course_management.dto.ApiResponse;
import com.example.BE_course_management.dto.request.RatingCreateRequest;
import com.example.BE_course_management.dto.response.RatingResponse;
import com.example.BE_course_management.service.RatingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingController {

    RatingService ratingService;

    @PostMapping
    public ApiResponse<RatingResponse> createRating (@RequestBody RatingCreateRequest request) {
        return ApiResponse.<RatingResponse>builder()
                .result(ratingService.createRating(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<RatingResponse>> readRatings() {
        return ApiResponse.<List<RatingResponse>>builder()
                .result(ratingService.readRatings())
                .build();
    }

    @GetMapping("/{ratingId}")
    public ApiResponse<RatingResponse> readRating(@PathVariable("ratingId") String id) {
        return ApiResponse.<RatingResponse>builder()
                .result(ratingService.readRating(id))
                .build();
    }

}

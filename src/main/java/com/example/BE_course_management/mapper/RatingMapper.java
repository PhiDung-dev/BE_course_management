package com.example.BE_course_management.mapper;

import com.example.BE_course_management.dto.request.RatingCreateRequest;
import com.example.BE_course_management.dto.response.RatingResponse;
import com.example.BE_course_management.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    Rating toRating(RatingCreateRequest request);
    RatingResponse toRatingResponse(Rating rating);
    List<RatingResponse> toRatingResponseList(List<Rating> ratings);
}

package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.RatingCreateRequest;
import com.example.BE_course_management.dto.response.PaymentResponse;
import com.example.BE_course_management.dto.response.RatingResponse;
import com.example.BE_course_management.entity.Payment;
import com.example.BE_course_management.entity.Rating;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.RatingMapper;
import com.example.BE_course_management.repository.RatingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingService {
    RatingRepository ratingRepository;
    RatingMapper ratingMapper;

    public RatingResponse createRating(RatingCreateRequest request)
    {
        Rating rating = ratingMapper.toRating(request);
        Rating saveRating = ratingRepository.save(rating);

        return ratingMapper.toRatingResponse(saveRating);
    }

    public RatingResponse readRating(String id)
    {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.RATING_NOT_FOUND));
        return ratingMapper.toRatingResponse(rating);
    }
    public List<RatingResponse> readRatings()
    {
        List<Rating> listRayting = ratingRepository.findAll();
        return ratingMapper.toRatingResponseList(listRayting);
    }

    public void deleteRating(String id)
    {
        if(!ratingRepository.existsById(id))
        {
            throw new AppException(ErrorCode.RATING_NOT_FOUND);
        }
        ratingRepository.deleteById(id);
    }



}

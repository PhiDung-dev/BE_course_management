package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.CartCreateRequest;
import com.example.BE_course_management.dto.response.CartResponse;
import com.example.BE_course_management.entity.Cart;
import com.example.BE_course_management.entity.Course;
import com.example.BE_course_management.entity.User;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.CartMapper;
import com.example.BE_course_management.repository.CartRepository;
import com.example.BE_course_management.repository.CourseRepository;
import com.example.BE_course_management.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {

    CartRepository cartRepository;
    CartMapper cartMapper;
    UserRepository userRepository;
    CourseRepository courseRepository;

    public CartResponse createCart(CartCreateRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(()->new AppException(ErrorCode.COURSE_NOT_FOUND));
        if(cartRepository.existsByUserAndCourse(user, course)) {
            throw new AppException(ErrorCode.COURSE_ALREADY_IN_CART);
        }
        Cart cart = Cart.builder()
                .user(user)
                .course(course)
                .build();
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }

    public List<CartResponse> readCarts(String userId) {
        if(!userRepository.existsById(userId)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        List<Cart> carts = cartRepository.findByUserId(userId);
        return cartMapper.toCartResponseList(carts);
    }

    public void deleteCart(String id) {
        if(!cartRepository.existsById(id)) {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND_IN_CART);
        }
        cartRepository.deleteById(id);
    }

}

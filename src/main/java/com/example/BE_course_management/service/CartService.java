package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.CartCreateRequest;
import com.example.BE_course_management.dto.request.RatingCreateRequest;
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

    public CartResponse addItemInCart(CartCreateRequest request)
    {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(()->new AppException(ErrorCode.COURSE_NOT_FOUND));
        if(cartRepository.existsByUserAndCourse(user,course))
        {
            throw new AppException(ErrorCode.COURSE_ALREADY_IN_CART);
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCourse(course);
        Cart saveCart = cartRepository.save(cart);
        return cartMapper.toCartResponse(saveCart);

    }

    public CartResponse searchCourseInCart(String userId, String title) {

        if (!userRepository.existsById(userId)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

         Cart cart = cartRepository
                .findByUserIdAndCourseTitle(userId, title.trim());
        if(cart == null){
            throw new AppException(ErrorCode.COURSE_NOT_FOUND_IN_CART);
        }
        return cartMapper.toCartResponse(cart);
    }
    public List<CartResponse> readCartsByUser(String userId) {

        if (!userRepository.existsById(userId)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        List<Cart> cartList = cartRepository.findByUserId(userId);


        return cartMapper.toCartResponseList(cartList);
    }

    public void deleteCart(String id)
    {
        if(!cartRepository.existsById(id))
        {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND_IN_CART);
        }
    }


}

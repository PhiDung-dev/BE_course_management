package com.example.BE_course_management.mapper;

import com.example.BE_course_management.dto.response.CartResponse;
import com.example.BE_course_management.entity.Cart;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, UserMapper.class})
public interface CartMapper {

  CartResponse toCartResponse(Cart cart);

  List<CartResponse> toCartResponseList(List<Cart> carts);

}

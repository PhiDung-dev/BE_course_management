package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.AccountCreateRequest;
import com.example.BE_course_management.dto.response.AccountResponse;
import com.example.BE_course_management.entity.Account;
import com.example.BE_course_management.entity.Role;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.mapper.AccountMapper;
import com.example.BE_course_management.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;
    //public AccountResponse createAccount(AccountCreateRequest request)


}

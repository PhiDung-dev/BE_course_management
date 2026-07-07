package com.example.BE_course_management.config;

import com.example.BE_course_management.entity.Account;
import com.example.BE_course_management.entity.Roles;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.repository.AccountRepository;
import com.example.BE_course_management.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppConfig {

    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            if(!accountRepository.existsByUsername("admin")) {
                Account account = Account.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .role(roleRepository.findByRoleName(Roles.ADMIN.name()).orElseThrow(()->new AppException(ErrorCode.ROLE_NOT_FOUND)))
                        .build();
                accountRepository.save(account);
            }
        };
    }

}

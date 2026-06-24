package com.example.BE_course_management.repository;

import com.example.BE_course_management.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
    boolean existsByUsername(String username);
    Account findByUsername(String username);
}

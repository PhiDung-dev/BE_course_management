package com.example.BE_course_management.mapper;

import com.example.BE_course_management.dto.request.AccountCreateRequest;
import com.example.BE_course_management.dto.request.AccountUpdateRequest;
import com.example.BE_course_management.dto.response.AccountResponse;
import com.example.BE_course_management.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountCreateRequest request);
    AccountResponse toAccountResponse(Account account);
    List<AccountResponse> toAccountResponseList(List<Account> accounts);

    void updateAccount(@MappingTarget Account account, AccountUpdateRequest request);
}

package com.example.BE_course_management.service;

import com.example.BE_course_management.dto.request.AuthenticationRequest;
import com.example.BE_course_management.dto.response.AuthenticationResponse;
import com.example.BE_course_management.entity.Account;
import com.example.BE_course_management.entity.AccountStatus;
import com.example.BE_course_management.exception.AppException;
import com.example.BE_course_management.exception.ErrorCode;
import com.example.BE_course_management.repository.AccountRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    @Value("${jwt.secret-key}")
    @NonFinal
    String secretKey;

    public AuthenticationResponse login(AuthenticationRequest request) throws JOSEException {
        Account account = accountRepository.findByUsername(request.getUsername()).orElseThrow(()->new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        if(account.getStatus() == AccountStatus.BLOCKED){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        if(!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return AuthenticationResponse.builder()
                .token(generateToken(account))
                .build();
    }

    private String generateToken(Account account) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getUsername())
                .issuer("course_management")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", account.getRole().getRoleName())
                .claim("accountId", account.getId())
                .build();
        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
        JWSSigner jwsSigner = new MACSigner(secretKey);
        signedJWT.sign(jwsSigner);
        return signedJWT.serialize();
    }

}

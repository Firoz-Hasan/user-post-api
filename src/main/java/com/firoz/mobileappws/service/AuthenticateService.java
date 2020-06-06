package com.firoz.mobileappws.service;

import com.firoz.mobileappws.dtos.LoginRequestDto;
import com.firoz.mobileappws.dtos.SignupRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticateService {
    ResponseEntity<?> authenticateUser(LoginRequestDto loginRequestDto);
    ResponseEntity<?> registerUser(SignupRequestDto signUpRequestDto);
}

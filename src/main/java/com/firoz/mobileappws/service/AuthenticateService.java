package com.firoz.mobileappws.service;

import com.firoz.mobileappws.dtos.LoginRequestDto;
import com.firoz.mobileappws.dtos.SignupRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticateService {
    ResponseEntity<?> signInUser(LoginRequestDto loginRequestDto);
    ResponseEntity<?> signUpUser(SignupRequestDto signUpRequestDto);
}

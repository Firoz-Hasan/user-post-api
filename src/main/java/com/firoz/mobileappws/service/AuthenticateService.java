package com.firoz.mobileappws.service;

import com.firoz.mobileappws.dtos.LoginRequest;
import com.firoz.mobileappws.dtos.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthenticateService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignupRequest signUpRequest);
}

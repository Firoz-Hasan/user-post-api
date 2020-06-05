package com.firoz.mobileappws.jpa;

import com.firoz.mobileappws.dtos.LoginRequest;
import com.firoz.mobileappws.dtos.SignupRequest;
import com.firoz.mobileappws.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

	@Autowired
	private AuthenticateService authenticateService;



	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		return authenticateService.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return authenticateService.registerUser(signUpRequest);
	}
}

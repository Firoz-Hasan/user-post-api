package com.firoz.mobileappws.serviceImpl;

import com.firoz.mobileappws.daos.AuthenticateDaoRepository;
import com.firoz.mobileappws.daos.RoleDaoRepository;
import com.firoz.mobileappws.dtos.JwtResponseDto;
import com.firoz.mobileappws.dtos.LoginRequestDto;
import com.firoz.mobileappws.dtos.MessageResponseDto;
import com.firoz.mobileappws.dtos.SignupRequestDto;
import com.firoz.mobileappws.models.AuthenticateUser;
import com.firoz.mobileappws.models.ERole;
import com.firoz.mobileappws.models.Role;
import com.firoz.mobileappws.service.AuthenticateService;
import com.firoz.mobileappws.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticateDaoRepository authenticateDaoRepository;

    @Autowired
    RoleDaoRepository roleDaoRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @Override
    public ResponseEntity<?> authenticateUser(LoginRequestDto loginRequestDto) {
        Authentication authentication =
                authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (loginRequestDto.getUsername(), loginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponseDto(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequestDto signUpRequestDto) {
        if (authenticateDaoRepository.existsByUsername(signUpRequestDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDto("Error: Username is already taken!"));
        }

        if (authenticateDaoRepository.existsByEmail(signUpRequestDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDto("Error: Email is already in use!"));
        }

        // Create new user's account
        AuthenticateUser user = new AuthenticateUser(signUpRequestDto.getUsername(),
                signUpRequestDto.getEmail(),
                encoder.encode(signUpRequestDto.getPassword()));

        Set<String> strRoles = signUpRequestDto.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleDaoRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleDaoRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleDaoRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        System.out.println("bla bla bla bla "+ modRole.toString());

                        break;
                    default:
                        Role userRole = roleDaoRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        System.out.println("bla bla bla bla "+ roleDaoRepository.findByName(ERole.ROLE_USER));
                }
            });
        }

        user.setRoles(roles);
        authenticateDaoRepository.save(user);

        return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
    }
}

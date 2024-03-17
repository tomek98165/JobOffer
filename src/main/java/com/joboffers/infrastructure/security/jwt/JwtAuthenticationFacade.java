package com.joboffers.infrastructure.security.jwt;

import com.joboffers.infrastructure.token.controller.dto.JwtResponseDto;
import com.joboffers.infrastructure.token.controller.dto.LoginRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JwtAuthenticationFacade {

    private final AuthenticationManager authenticationManager;
    public JwtResponseDto authenticateAndGenerateToken(LoginRequestDto loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        return JwtResponseDto.builder().build();
    }
}

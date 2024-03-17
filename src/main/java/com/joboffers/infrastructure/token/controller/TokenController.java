package com.joboffers.infrastructure.token.controller;

import com.joboffers.infrastructure.security.jwt.JwtAuthenticationFacade;
import com.joboffers.infrastructure.token.controller.dto.JwtResponseDto;
import com.joboffers.infrastructure.token.controller.dto.LoginRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenController {

    private final JwtAuthenticationFacade jwtAuthenticationFacade;

    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(@Valid @RequestBody LoginRequestDto loginRequest){
        final JwtResponseDto jwtResponse = jwtAuthenticationFacade.authenticateAndGenerateToken(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}

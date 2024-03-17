package com.joboffers.infrastructure.loginandregister.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequestDto(

        @NotBlank(message = "username.not.blank")
        String username,
        @NotBlank(message = "password.not.blank")
        String password
) {
}

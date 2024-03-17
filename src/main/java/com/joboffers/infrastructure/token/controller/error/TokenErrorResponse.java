package com.joboffers.infrastructure.token.controller.error;

import org.springframework.http.HttpStatus;

public record TokenErrorResponse(
        String message,
        HttpStatus status
) {
}

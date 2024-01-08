package com.joboffers.infrastructure.offer.controller.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record OfferRestControllerErrorResponse(
        String message,
        HttpStatus status
) {
}

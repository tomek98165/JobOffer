package com.joboffers.infrastructure.apivalidation;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record ApiValidateErrorDto(
        List<String> message,
        HttpStatus status
) {
}

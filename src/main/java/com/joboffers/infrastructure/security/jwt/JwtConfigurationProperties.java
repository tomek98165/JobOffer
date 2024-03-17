package com.joboffers.infrastructure.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("auth.jwt")
public record JwtConfigurationProperties(
        String secret,
        long expirationDays,
        String issuer
) {
}

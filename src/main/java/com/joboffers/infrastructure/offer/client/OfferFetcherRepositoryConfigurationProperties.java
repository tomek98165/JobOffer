package com.joboffers.infrastructure.offer.client;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@ConfigurationProperties(prefix = "offer.http.client.config")
public record OfferFetcherRepositoryConfigurationProperties(String uri,
                                                            int port,
                                                            long connectionTimeout,
                                                            long readTimeout
) {
}

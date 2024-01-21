package com.joboffers.http.error;

import com.joboffers.domain.offer.OfferFetchRepository;
import com.joboffers.infrastructure.offer.client.OfferFetchRepositoryImpl;
import com.joboffers.infrastructure.offer.client.OfferFetcherRepositoryConfiguration;
import com.joboffers.infrastructure.offer.client.OfferFetcherRepositoryConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OffersRestTemplateTestConfig extends OfferFetcherRepositoryConfiguration {


    public OffersRestTemplateTestConfig(OfferFetcherRepositoryConfigurationProperties properties) {
        super(properties);
    }

    public OfferFetchRepository remoteOfferTestClient() {
        final RestTemplate restTemplate = restTemplate(restTemplateResponseErrorHandler());
        return new OfferFetchRepositoryImpl(restTemplate, properties.uri(), properties.port());
    }

}

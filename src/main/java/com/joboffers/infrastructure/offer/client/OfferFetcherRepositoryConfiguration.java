package com.joboffers.infrastructure.offer.client;

import com.joboffers.domain.offer.OfferFetchRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@AllArgsConstructor
public class OfferFetcherRepositoryConfiguration {

    public final OfferFetcherRepositoryConfigurationProperties properties;



    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler(){
        return new RestTemplateResponseErrorHandler();
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler){
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(properties.connectionTimeout()))
                .setReadTimeout(Duration.ofMillis(properties.readTimeout()))
                .build();
    }

    @Bean
    public OfferFetchRepository remoteOfferClient(RestTemplate restTemplate) {
        return new OfferFetchRepositoryImpl(restTemplate, properties.uri(), properties.port());
    }
}

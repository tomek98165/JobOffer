package com.joboffers.infrastructure.offer.client;

import com.joboffers.domain.offer.OfferFetchRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

@Configuration
public class OfferFetcherRepositoryConfiguration {

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler(){
        return new RestTemplateResponseErrorHandler();
    }


    @Bean
    public RestTemplate restTemplate(@Value("${offer.http.client.config.connectionTimeout}") long connectionTimeout,
                                     @Value("${offer.http.client.config.readTimeout}") long readTimeout,
                                     RestTemplateResponseErrorHandler restTemplateResponseErrorHandler){
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public OfferFetchRepository remoteOfferClient(RestTemplate restTemplate,
                                                  @Value("${offer.http.client.config.uri:http://example.com}") String uri,
                                                  @Value("${offer.http.client.config.port:5057}") int port) {
        return new OfferFetchRepositoryImpl(restTemplate, uri, port);
    }
}

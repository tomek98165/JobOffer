package com.joboffers;

import com.joboffers.infrastructure.offer.client.OfferFetcherRepositoryConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({OfferFetcherRepositoryConfigurationProperties.class})

public class JobOffersSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobOffersSpringBootApplication.class, args);
    }
}

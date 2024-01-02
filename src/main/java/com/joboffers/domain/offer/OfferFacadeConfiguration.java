package com.joboffers.domain.offer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferFacadeConfiguration {

    @Bean
    OfferFacade offerFacade(OfferRepository repository, OfferFetchRepository offerFetchRepository){
        return new OfferFacade(repository, offerFetchRepository);
    }
}

package com.joboffers.domain.offer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class OfferFacadeConfiguration {

    @Bean
    OfferRepository repository(){
        return new OfferRepository() {
            @Override
            public boolean offerUrlExist(String url) {
                return false;
            }

            @Override
            public Optional<Offer> findOfferById(String id) {
                return Optional.empty();
            }

            @Override
            public Offer save(Offer offer) {
                return null;
            }

            @Override
            public List<Offer> findAllOffers() {
                return null;
            }

            @Override
            public List<Offer> saveAll(List<Offer> offers) {
                return null;
            }
        };
    }

    @Bean
    OfferFacade offerFacade(OfferRepository offerRepository, OfferFetchRepository offerFetchRepository){

        return new OfferFacade(offerRepository, offerFetchRepository);
    }
}

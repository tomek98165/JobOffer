package com.joboffers.domain.offer;

import java.util.List;
import java.util.Optional;

public interface OfferRepository {


    boolean offerUrlExist(String url);
    Optional<Offer> findOfferById(String id);

    Offer save(Offer offer);

    List<Offer> findAllOffers();

    List<Offer> saveAll(List<Offer> offers);

}

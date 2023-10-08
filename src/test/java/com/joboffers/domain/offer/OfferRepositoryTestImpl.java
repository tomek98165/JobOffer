package com.joboffers.domain.offer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OfferRepositoryTestImpl implements OfferRepository {





    Map<String,Offer> offers = new ConcurrentHashMap<>();

    @Override
    public boolean offerUrlExist(String url) {

        return offers.values()
                .stream()
                .filter(offer -> offer.url().equals(url))
                .count() == 1;
    }

    @Override
    public Optional<Offer> findOfferById(String id) {
        return Optional.ofNullable(offers.get(id));
    }

    @Override
    public Offer save(Offer offer) {
        if(offerUrlExist(offer.url()))
            throw new OfferUrlExistException("Offer url: " + offer.url() + " exist");
        UUID id = UUID.randomUUID();
        Offer newOffer = Offer.builder()
                .url(offer.url())
                .salary(offer.salary())
                .company(offer.company())
                .position(offer.position())
                .id(id.toString())
                .build();

        offers.put(newOffer.id(), newOffer);
        return newOffer;
    }

    @Override
    public List<Offer> findAllOffers() {
        return offers.values().stream().toList();
    }

    @Override
    public List<Offer> saveAll(List<Offer> offers) {
        List<Offer> newOffers = offers.stream()
                .filter(offer -> !offerUrlExist(offer.url()))
                .toList();
        return newOffers.stream()
                .map(this::save)
                .toList();
    }
}

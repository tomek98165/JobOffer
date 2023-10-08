package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.NewOfferDto;

import java.util.List;

public class OfferFetchRepositoryTestImpl implements OfferFetchRepository {

    List<NewOfferDto> offers;

    OfferFetchRepositoryTestImpl(List<NewOfferDto> offers){
        this.offers = offers;
    }

    @Override
    public List<NewOfferDto> fetchAllOffers() {
        return offers;
    }
}

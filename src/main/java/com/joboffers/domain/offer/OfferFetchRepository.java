package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.NewOfferDto;

import java.util.List;

public interface OfferFetchRepository {

    List<NewOfferDto> fetchAllOffers();
}

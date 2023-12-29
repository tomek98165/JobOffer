package com.joboffers.infrastructure.offer.scheduler;

import com.joboffers.domain.offer.OfferFacade;
import com.joboffers.domain.offer.dto.OfferDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class OfferFetcherScheduler {

    private final OfferFacade offerFacade;


    @Scheduled(fixedDelayString = "${offer.http.scheduler.delay}")
    public void fetchOffersFromHttp(){
        log.info("Starting fetching offers");
        List<OfferDto> fetchedOffers = offerFacade.fetchAllOffersAndSaveIfDontExist();
        log.info("Saved {} new Offers", fetchedOffers.size());

    }


}

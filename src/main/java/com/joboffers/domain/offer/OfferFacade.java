package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.NewOfferDto;
import com.joboffers.domain.offer.dto.OfferDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OfferFacade {

    OfferRepository offerRepository;
    OfferFetchRepository offerFetchRepository;


    public OfferDto findOfferById(String id){
        return offerRepository.findById(id)
                .map(offer -> OfferDto.builder()
                        .id(offer.id())
                        .offerUrl(offer.url())
                        .salary(offer.salary())
                        .company(offer.company())
                        .position(offer.position())
                        .build())
                .orElseThrow(() -> new OfferNotFoundException("Offer not found"));
    }

    public OfferDto addNewOffer(NewOfferDto newOfferDto){
        Offer offer = Offer.builder()
                .company(newOfferDto.company())
                .position(newOfferDto.position())
                .salary(newOfferDto.salary())
                .url(newOfferDto.url())
                .build();
        Offer savedOffer = offerRepository.save(offer);
        return OfferDto.builder()
                .salary(savedOffer.salary())
                .id(savedOffer.id())
                .position(savedOffer.position())
                .company(savedOffer.company())
                .offerUrl(savedOffer.url())
                .build();
    }

    public List<OfferDto> findAllOffers(){
        return offerRepository.findAll()
                .stream()
                .map(offer -> OfferDto.builder()
                        .salary(offer.salary())
                        .position(offer.position())
                        .company(offer.company())
                        .offerUrl(offer.url())
                        .id(offer.id())
                        .build()
                ).toList();
    }

    public List<OfferDto> fetchAllOffersAndSaveIfDontExist(){
            List<NewOfferDto> offersFromHttp = offerFetchRepository.fetchAllOffers();
            List<NewOfferDto> filteredOffers = filterNotExistingOffers(offersFromHttp);
            List<Offer> savedOffers = offerRepository.saveAll(
                    filteredOffers.stream()
                            .map(newOfferDto -> Offer.builder()
                                    .url(newOfferDto.url())
                                    .company(newOfferDto.company())
                                    .position(newOfferDto.position())
                                    .salary(newOfferDto.salary())
                                    .build()
                            )
                            .toList()
            );
            return savedOffers.stream()
                .map(offer -> OfferDto.builder()
                        .salary(offer.salary())
                        .position(offer.position())
                        .company(offer.company())
                        .offerUrl(offer.url())
                        .id(offer.id())
                        .build())
                .toList();
    }

    private List<NewOfferDto> filterNotExistingOffers(List<NewOfferDto> jobOffers) {
        return jobOffers.stream()
                .filter(offerDto -> !offerDto.url().isEmpty())
                .filter(offerDto -> !offerRepository.existsOfferByUrl(offerDto.url()))
                .collect(Collectors.toList());
    }


}

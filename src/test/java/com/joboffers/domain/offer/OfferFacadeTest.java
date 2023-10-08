package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.NewOfferDto;
import com.joboffers.domain.offer.dto.OfferDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class OfferFacadeTest {

    OfferFacade offerFacade = new OfferFacade(
            new OfferRepositoryTestImpl(),
            new OfferFetchRepositoryTestImpl(List.of(
                    new NewOfferDto("hello.pl", "aaa", "aaa", "1100"),
                    new NewOfferDto("hello2.pl", "aaa", "aaa", "1100"),
                    new NewOfferDto("hello3.pl", "aaa", "aaa", "1100"),
                    new NewOfferDto("hello4.pl", "aaa", "aaa", "1100"),
                    new NewOfferDto("hello5.pl", "aaa", "aaa", "1100"),
                    new NewOfferDto("hello6.pl", "aaa", "aaa", "1100")
            )) {
    });

    @Test
    public void should_save_new_offer(){
        OfferDto newOffer = offerFacade.addNewOffer(new NewOfferDto(
                "url",
                "company",
                "position",
                "salary"));



        assertThat(newOffer).isEqualTo(OfferDto.builder()
                        .id(newOffer.id())
                        .url("url")
                        .company("company")
                        .position("position")
                        .salary("salary")
                        .build());
    }
    @Test
    public void should_find_offer_by_id_in_memory(){
        OfferDto newOffer = offerFacade.addNewOffer(new NewOfferDto(
                "url",
                "company",
                "position",
                "salary"));

        OfferDto foundOffer = offerFacade.findOfferById(newOffer.id());

        assertThat(foundOffer).isEqualTo(OfferDto.builder()
                .id(newOffer.id())
                .url("url")
                .company("company")
                .position("position")
                .salary("salary")
                .build());
    }
    @Test
    public void should_throw_exception_when_id_offer_doesnt_exist(){

        Throwable thrown = catchThrowable(() -> offerFacade.findOfferById("123"));

        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferNotFoundException.class)
                .hasMessage("Offer not found");
    }
    @Test
    public void should_throw_exception_when_offer_url_already_exist(){

        offerFacade.addNewOffer(new NewOfferDto(
                "url",
                "company",
                "position",
                "salary"));

        Throwable thrown = catchThrowable(() -> offerFacade.addNewOffer(new NewOfferDto(
                "url",
                "company",
                "position",
                "salary")));

        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferUrlExistException.class)
                .hasMessage("Offer url: url exist");

    }
    @Test
    public void should_fetch_all_offers_and_save_all(){

        List<OfferDto> result = offerFacade.fetchAllOffersAndSaveIfDontExist();

        assertThat(result.stream()
                .map(offerDto -> new NewOfferDto(
                        offerDto.url(),
                        offerDto.company(),
                        offerDto.position(),
                        offerDto.salary()
                ))
                .toList()
        ).containsExactlyInAnyOrder(
                new NewOfferDto("hello.pl", "aaa", "aaa", "1100"),
                new NewOfferDto("hello2.pl", "aaa", "aaa", "1100"),
                new NewOfferDto("hello3.pl", "aaa", "aaa", "1100"),
                new NewOfferDto("hello4.pl", "aaa", "aaa", "1100"),
                new NewOfferDto("hello5.pl", "aaa", "aaa", "1100"),
                new NewOfferDto("hello6.pl", "aaa", "aaa", "1100")
        );



    }
    @Test
    public void should_save_2_offers_from_http_when_repository_has_4_offer_url_exist(){

        offerFacade.addNewOffer(new NewOfferDto("hello.pl", "aaa", "aaa", "1100"));
        offerFacade.addNewOffer(new NewOfferDto("hello2.pl", "aaa", "aaa", "1100"));
        offerFacade.addNewOffer(new NewOfferDto("hello3.pl", "aaa", "aaa", "1100"));
        offerFacade.addNewOffer(new NewOfferDto("hello4.pl", "aaa", "aaa", "1100"));

        List<OfferDto> result = offerFacade.findAllOffers();
        assertThat(result).hasSize(4);

        List<OfferDto> result2 = offerFacade.fetchAllOffersAndSaveIfDontExist();

        assertThat(result2.stream()
                .map(offerDto -> new NewOfferDto(
                        offerDto.url(),
                        offerDto.company(),
                        offerDto.position(),
                        offerDto.salary()
                ))
                .toList()
        ).containsExactlyInAnyOrder(
                new NewOfferDto("hello5.pl", "aaa", "aaa", "1100"),
                new NewOfferDto("hello6.pl", "aaa", "aaa", "1100")
        );





    }

}
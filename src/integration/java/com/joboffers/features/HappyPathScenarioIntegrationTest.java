package com.joboffers.features;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.joboffers.BaseIntegrationTest;
import com.joboffers.SampleJsonOffersResponse;
import com.joboffers.domain.offer.dto.OfferDto;
import com.joboffers.infrastructure.offer.scheduler.OfferFetcherScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HappyPathScenarioIntegrationTest extends BaseIntegrationTest implements SampleJsonOffersResponse {


    @Autowired
    OfferFetcherScheduler offerFetcherScheduler;

    @Test
    public void user_want_to_see_offers_but_have_to_be_logged_in_and_external_server_should_have_some_offers() throws Exception {
        // step 1: there are no offers in external HTTP server
        // given & when
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(bodyWithNoOffersJson())));

        // then
        offerFetcherScheduler.fetchOffersFromHttp();


        // step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database
        // step 3: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned UNAUTHORIZED(401)
        // step 4: user made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
        // step 5: user made POST /register with username=someUser, password=somePassword and system registered user with status OK(200)
        // step 6: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC


        // step 7: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers
        // given & when
        ResultActions perform = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();
        String jsonWithOffers =  mvcResult.getResponse().getContentAsString();
        List<OfferDto> offers = objectMapper.readValue(jsonWithOffers, new TypeReference<>() {
        });
        assertThat(offers).isEmpty();


        // step 8: there are 2 new offers in external HTTP server
        // step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database
        // step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1000 and 2000


        // step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer not found”
        // given & when
        ResultActions performGetIdNotFound = mockMvc.perform(get("/offers/9999")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        //then
        performGetIdNotFound.andExpect(status().isNotFound())
                .andExpect(content().json("""
                                    {
                                    "message": "Offer not found",
                                    "status": "NOT_FOUND"
                                    }
                                    """.trim()
                        )
                );


        // step 12: user made GET /offers/1000 and system returned OK(200) with offer
        // step 13: there are 2 new offers in external HTTP server
        // step 14: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 3000 and 4000 to database
        // step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 offers with ids: 1000,2000, 3000 and 4000


        // step 16: user made POST /offers with header "Authorization: Bearer AAAA.BBBB.CCC" and offer as body and system returned CREATED(201) with saved offer
        // given & when
        ResultActions performPostAddNewOffer = mockMvc.perform(post("/offers")
                .content("""
                        {
                        "offerUrl": "test.com",
                        "title":"Junior Java Developer",
                        "salary":"10k-15k",
                        "company":"test"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        String createdOfferJson = performPostAddNewOffer.andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        OfferDto parsedCreatedOfferJson = objectMapper.readValue(createdOfferJson, OfferDto.class);
        assertAll(
                ()->assertThat(parsedCreatedOfferJson.id()).isNotEmpty(),
                ()->assertThat(parsedCreatedOfferJson.offerUrl()).isEqualTo("test.com"),
                ()->assertThat(parsedCreatedOfferJson.company()).isEqualTo("test"),
                ()->assertThat(parsedCreatedOfferJson.salary()).isEqualTo("10k-15k"),
                ()->assertThat(parsedCreatedOfferJson.position()).isEqualTo("Junior Java Developer")
        );


        // step 17: user made GET /offers with header "Authorization: Bearer AAAA.BBBB.CCC" and system returned OK(200) with 1 offer
        ResultActions performfindAllOffers = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        //then
        String oneOffersJson = performfindAllOffers.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<OfferDto> parsedOneOffer = objectMapper.readValue(oneOffersJson, new TypeReference<>(){});

        assertThat(parsedOneOffer).hasSize(1);
    }
}

package com.joboffers.infrastructure.offer.client;

import com.joboffers.domain.offer.OfferFetchRepository;
import com.joboffers.domain.offer.dto.NewOfferDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;


@AllArgsConstructor
@Log4j2
public class OfferFetchRepositoryImpl implements OfferFetchRepository {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public List<NewOfferDto> fetchAllOffers() {
        log.info("Started fetching offers from http");
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);

        try {
            String urlForService = getUrlForService("/offers");
            final String url = UriComponentsBuilder.fromHttpUrl(urlForService).toUriString();
            ResponseEntity<List<NewOfferDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<>() {
                    }
            );
            final List<NewOfferDto> body = response.getBody();
            if (body == null) {
                log.info("Server http is empty");
                return Collections.emptyList();
            }
            else {
                log.info("Success response body returned: " + body);
                return body;
            }

        }
        catch(ResourceAccessException e){
            log.error("ERROR while fetching " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }
}

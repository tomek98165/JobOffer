package com.joboffers.domain.offer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NewOfferDto(
                          @JsonProperty("OfferUrl") String url,
                          String company,
                          @JsonProperty("title")   String position,
                          String salary
) {
}

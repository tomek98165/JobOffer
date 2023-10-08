package com.joboffers.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferDto(String id,
                       String url,
                       String position,
                       String company,
                       String salary) {
}

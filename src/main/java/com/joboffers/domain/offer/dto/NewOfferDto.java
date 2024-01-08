package com.joboffers.domain.offer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record NewOfferDto(
                          @JsonProperty("offerUrl")
                          @NotNull(message="url.not.null")
                          @NotBlank(message="url.not.blank")
                          String url,
                          @JsonProperty("company")
                          @NotNull(message="companyName.not.null")
                          @NotBlank(message="companyName.not.blank")
                          String company,
                          @JsonProperty("title")
                          @NotNull(message="position.not.null")
                          @NotBlank(message="position.not.blank")
                          String position,
                          @JsonProperty("salary")
                          @NotNull(message="salary.not.null")
                          @NotBlank(message="salary.not.blank")
                          String salary
) {
}

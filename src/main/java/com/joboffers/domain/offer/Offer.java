package com.joboffers.domain.offer;

import lombok.Builder;

@Builder
public record Offer(String id,
                    String url,
                    String position,
                    String company,
                    String salary) {
}

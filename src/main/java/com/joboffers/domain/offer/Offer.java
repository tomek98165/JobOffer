package com.joboffers.domain.offer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Document
public record Offer(
                    @Id String id,
                    @Indexed(unique=true) String url,
                    String position,
                    String company,
                    String salary) {
}

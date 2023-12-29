package com.joboffers.scheduler;


import com.joboffers.BaseIntegrationTest;
import com.joboffers.JobOffersSpringBootApplication;
import com.joboffers.domain.offer.OfferFetchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Duration;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest(classes = JobOffersSpringBootApplication.class, properties = "offer.http.scheduler.enabled=true")
public class OfferFetcherSchedulerTest extends BaseIntegrationTest {

    @SpyBean
    OfferFetchRepository offerFetchRepository;

    @Test
    public void should_run_scheduler_2_times_in_2_seconds(){
        await().
                atMost(Duration.ofSeconds(2))
                .untilAsserted(() -> verify(offerFetchRepository, times(2)).fetchAllOffers());
    }
}

package com.joboffers.controller.error;

import com.joboffers.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OfferUrlDuplicationErrorTest extends BaseIntegrationTest {


    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
    @Test
    public void should_return_409_conflict_when_url_exist() throws Exception{
        // given & then
        mockMvc.perform(post("/offers")
                .content("""
                        {
                        "offerUrl": "test.com",
                        "title":"Junior Java Developer",
                        "salary":"10k-15k",
                        "company":"test"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON));

        ResultActions performWithDuplicateKeyException = mockMvc.perform(post("/offers")
                .content("""
                        {
                        "offerUrl": "test.com",
                        "title":"Junior Java Developer",
                        "salary":"10k-15k",
                        "company":"test"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON));

        performWithDuplicateKeyException.andExpect(status().isConflict());



    }
}

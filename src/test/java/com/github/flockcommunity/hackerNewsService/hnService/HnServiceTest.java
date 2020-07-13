package com.github.flockcommunity.hackerNewsService.hnService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class HnServiceTest {

    @Autowired
    HnService hnService;

    @Test
    public void newStoriesFlux() {
        StepVerifier.create(hnService.getNewStoriesPublisher().log())
                .expectSubscription()
                .expectNextCount(250L)
                .thenCancel()
                .verify();
    }

}
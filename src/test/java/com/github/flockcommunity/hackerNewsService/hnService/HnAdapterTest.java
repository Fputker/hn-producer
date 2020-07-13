package com.github.flockcommunity.hackerNewsService.hnService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class HnAdapterTest {

    private final HnAdapter adapter;

    @Autowired
    HnAdapterTest(HnAdapter adapter) {
        this.adapter = adapter;
    }

    @Test
    void getNewStories() {
        Flux<Long> flux = adapter.getNewStories().log();
        StepVerifier.create(flux)
                .expectSubscription()
                .expectNextCount(500l)
                .consumeSubscriptionWith((element) -> System.out.println("Element = " + element))
                .verifyComplete();
    }

    @Test
    void getStory() {
        StepVerifier.create(adapter.getStory(23573016).log())
                .expectSubscription()
                .consumeNextWith((element) -> System.out.println("Element = " + element))
                .verifyComplete();
    }

    @Test
    public void ifGetTtitle_thenGetTitle() {
        StepVerifier.create(adapter.getStoryTitle(23573016).log())
                .expectSubscription()
                .expectNextMatches(e -> e.equals("Linear Regression"))
                .verifyComplete();

    }

}
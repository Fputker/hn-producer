package com.github.flockcommunity.hackerNewsService.hnService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Consumer;

@SpringBootTest
class HnServiceTest {

    @Autowired
    HnService hnService;

    @Test
    void getTopStories() {

        Mono<TopStories> result = hnService.getLatestTopStories();

        StepVerifier.create(result.log())
                .assertNext((Consumer) o -> {
                    TopStories topStories = (TopStories) o;

                    System.out.println();
                    System.out.println(topStories.getStoryList());
                    System.out.println(o);
                    System.out.println();
                })
                .verifyComplete();
    }

    @Test
    void getStory() {

        StepVerifier.create(hnService.getStory(23573016).log())
                .expectNextCount(1L)
                .verifyComplete();
    }

}
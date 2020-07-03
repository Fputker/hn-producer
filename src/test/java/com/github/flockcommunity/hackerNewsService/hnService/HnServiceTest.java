package com.github.flockcommunity.hackerNewsService.hnService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void newStories() {
        List<String> old = Arrays.asList("foo", "bar", "baz");
        List<String> later = Arrays.asList("baz", "hello", "world");
        List<String> expected = Arrays.asList("hello", "world");

        List<String> result = hnService.newStories(old, later);
        System.out.printf(result.toString());
        assertEquals(result, expected);
    }

    @Test
    public void newStoriesFlux() {
        StepVerifier.create(hnService.newStoriesFlux().log())
                .expectSubscription()
                .expectNextCount(20L)
                .thenCancel()
                .verify();
    }

    @Test
    void getStory() {

        StepVerifier.create(hnService.getStory(23573016).log())
                .expectSubscription()
                .expectNextCount(1l)
                .consumeSubscriptionWith((element) -> System.out.println("Element = " + element))
                .verifyComplete();
    }
}
package com.github.flockcommunity.hackerNewsService.hnService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class HnService {


    Mono<TopStories> getLatestTopStories() {
        return getTopStoriesDTO().map(it -> mapToTopStories(it));
    }

    private Mono<List> getTopStoriesDTO() {
        WebClient HnClient = WebClient.create(HnEndpoints.NEWSTORIES_ENDPOINT_V0);
        return HnClient.get()
                .retrieve()
                .bodyToMono(List.class);
    }

    private TopStories mapToTopStories(List hnTopStories) {
        return new TopStories(hnTopStories.subList(0, 10));
    }
    Flux<?> getSingleStringFlux() {
        return getTopStoriesDTO()
                .flatMapMany(Flux::fromIterable)
                .take(10L);
    }

    Flux newStoriesFlux() {
        List oldStories = new ArrayList<String>();
        Random random = new Random();

        return Flux.interval(Duration.ofSeconds(0), Duration.ofSeconds( random.nextInt(30)))
                .flatMap(it -> getTopStoriesDTO())
                .map(it -> it.subList(0, 10))
                .map(it -> {
                    List newStories = newStories(oldStories, it);
                    oldStories.addAll(newStories);
                    return newStories;
                })
                .flatMap(Flux::fromIterable);
    }

    List newStories(List<?> oldStories, List<?> stories) {
        return stories.stream()
                .filter(e -> !oldStories.contains(e))
                .collect(Collectors.toList());
    }

    Flux<TopStories> getTopStories() {
        return Flux.interval(Duration.ofSeconds(10L))
                .flatMap(it -> getLatestTopStories());
    }

    Mono<Story> getStory(int id) {
        return WebClient.create(HnEndpoints.ITEM_ENDPOINT_V0.concat(String.valueOf(id)).concat(".json?print=pretty"))
                .get()
                .retrieve()
                .onStatus(HttpStatus::isError, it -> Mono.error(new RuntimeException("HELP!")))
                .bodyToMono(Story.class);
    }

    
}

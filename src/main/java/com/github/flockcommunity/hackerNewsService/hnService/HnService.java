package com.github.flockcommunity.hackerNewsService.hnService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class HnService {



    Mono<TopStories> getLatestTopStories() {
        return getTopStoriesDTO().map(it -> mapToTopStories(it));
    }

    private Mono<List> getTopStoriesDTO() {
        WebClient HnClient = WebClient.create(HnEndpoints.TOPSTORIES_ENDPOINT_V0);
        return HnClient.get()
                .retrieve()
                .bodyToMono(List.class);
    }

    private TopStories mapToTopStories(List hnTopStories) {
        return new TopStories(hnTopStories.subList(0, 10));
    }

    Flux getSingleStringFlux () {
        return getTopStoriesDTO()
                .flatMapMany(it -> Flux.fromIterable(it))
                .take(10L);
    }

    Flux<String> newStories () {
        List oldStories = new ArrayList();

        return Flux.interval(Duration.ofSeconds(10L))
                .flatMap(it -> getTopStoriesDTO())
                .map(it -> it.subList(0,10))
                .map(it -> {
                    oldStories.clear();
                    oldStories.addAll(it);
                    return it;
                })
                .flatMap(it -> Flux.fromIterable(it));

    }


    Flux<TopStories> getTopStories() {
        return Flux.interval(Duration.ofSeconds(10L))
                .flatMap(it -> getLatestTopStories());
    }

    Mono<Story> getStory(int id) {
        return WebClient.create(HnEndpoints.ITEM_ENDPOINT_V0.concat(String.valueOf(id)).concat(".json"))
                .get()
                .retrieve()
                .onStatus(HttpStatus::isError, it -> Mono.error(new RuntimeException("HELP!")))
                .bodyToMono(Story.class);
    }
}

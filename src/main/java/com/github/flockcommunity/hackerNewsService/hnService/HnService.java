package com.github.flockcommunity.hackerNewsService.hnService;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class HnService {

    Mono<TopStories> getLatestTopStories () {
        return getTopStoriesDTO().map(it -> mapToTopStories(it));
    }

    private Mono<ArrayList> getTopStoriesDTO() {
        WebClient HnClient =  WebClient.create(HnEndpoints.TOPSTORIES_ENDPOINT_V0);
        return HnClient.get()

                .retrieve()
                .bodyToMono(ArrayList.class);
    }

    private TopStories mapToTopStories (List hnTopStories) {
        return new TopStories(hnTopStories.subList(0,10));
    }

    Flux<TopStories> getTopStories () {
        return Flux.interval(Duration.ofSeconds(10L))
                .flatMap(it -> getLatestTopStories());
    }



}

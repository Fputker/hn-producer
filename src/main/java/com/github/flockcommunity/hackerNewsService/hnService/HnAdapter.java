package com.github.flockcommunity.hackerNewsService.hnService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HnAdapter {

    Flux<Long> getNewStories() {
        WebClient HnClient = WebClient.create(HnEndpoints.NEWSTORIES_ENDPOINT_V0);
        return HnClient.get()
                .retrieve()
                .bodyToFlux(Long.class);
    }

    Mono<Story> getStory(int id) {
        return WebClient.create(HnEndpoints.ITEM_ENDPOINT_V0.concat(String.valueOf(id)).concat(".json?print=pretty"))
                .get()
                .retrieve()
                .onStatus(HttpStatus::isError, it -> Mono.error(new RuntimeException("HELP!")))
                .bodyToMono(Story.class);
    }

    public Mono<String> getStoryTitle(int id) {
        Mono<Story> story = getStory(id);
        return story.map(Story::getTitle);
    }
}

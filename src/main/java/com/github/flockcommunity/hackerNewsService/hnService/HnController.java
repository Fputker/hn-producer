package com.github.flockcommunity.hackerNewsService.hnService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(path="/stories")
public class HnController {

    private final HnService hnService;

    public HnController(HnService hnService) {
        this.hnService = hnService;
    }

    @GetMapping(path = "/latest-topstories")
    Mono<TopStories> getLatestTopStories () {
        return hnService.getLatestTopStories().log();
    }

    @GetMapping(path = "/topstories")
    Flux<TopStories> getTopStories () {
        return hnService.getTopStories();
    }

}

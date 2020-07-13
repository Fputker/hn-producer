package com.github.flockcommunity.hackerNewsService.hnService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController()
@RequestMapping(path="/stories")
public class HnController {

    private final HnService hnService;

    public HnController(HnService hnService) {
        this.hnService = hnService;
    }

//    @GetMapping(path = "/latest-topstories")
//    Mono<TopStories> getLatestTopStories () {
//        return hnService.getNewStories().log();
//    }
//
    @GetMapping(path = "/newstories")
    Flux<Long> getNewStories () {
        return hnService.getNewStoriesPublisher();
    }

//    @GetMapping(path = "/{id}")
//    Mono<Story> getStory (@PathVariable int id) {
//        return hnService.getStoryTitle(id);
//    }

}

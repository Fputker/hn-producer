package com.github.flockcommunity.hackerNewsService.hnService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class HnService {

    private final HnAdapter adapter;
    private final List<Long> storyList = new ArrayList<Long>();
    Random random = new Random();

    private final Flux<Long> newStoriesPublisher = newStoriesFlux().cache(250);

    @Autowired
    public HnService(HnAdapter adapter) {
        this.adapter = adapter;
    }

    private Flux<Long> newStoriesFlux() {

        return Flux.interval(Duration.ofSeconds(0), Duration.ofSeconds(random.nextInt(10)))
                .log()
                .flatMap(it -> adapter.getNewStories())
                .filter(storyId-> {
                    if  (!storyList.contains(storyId)){
                        storyList.add(storyId);
                        return true;
                    } else {
                        return false;
                    }
                });
    }

    public Flux<Long> getNewStoriesPublisher() {
        return newStoriesPublisher;
    }
}

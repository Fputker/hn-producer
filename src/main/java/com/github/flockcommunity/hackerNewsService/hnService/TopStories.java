package com.github.flockcommunity.hackerNewsService.hnService;

import java.util.List;

public class TopStories {

    public List getStoryList() {
        return storyList;
    }

    private List storyList;

    TopStories(List array) {
        storyList = array;
    }

    @Override
    public String toString() {
        return "TopStories{ storyList: " + storyList + "}";
    }
}

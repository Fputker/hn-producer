package com.github.flockcommunity.hackerNewsService.hnService;

import java.util.List;

public class TopStories {

    private List storyList;

    TopStories(List array) {
        storyList = array;
    }

    public List getStoryList() {
        return storyList;
    }

    @Override
    public String toString() {
        return "TopStories{ storyList: " + storyList + "}";
    }
}

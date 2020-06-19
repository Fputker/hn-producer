package com.github.flockcommunity.hackerNewsService.hnService;

import com.fasterxml.jackson.annotation.*;
import org.junit.platform.commons.util.ToStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "by",
        "descendants",
        "id",
        "kids",
        "score",
        "time",
        "title",
        "type",
        "url"
})
public class Story {

    @JsonProperty("by")
    private String by;
    @JsonProperty("descendants")
    private Integer descendants;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("kids")
    private List<Integer> kids = null;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Story() {
    }

    /**
     * @param score
     * @param by
     * @param id
     * @param time
     * @param title
     * @param type
     * @param descendants
     * @param url
     * @param kids
     */
    public Story(String by, Integer descendants, Integer id, List<Integer> kids, Integer score, Integer time, String title, String type, String url) {
        super();
        this.by = by;
        this.descendants = descendants;
        this.id = id;
        this.kids = kids;
        this.score = score;
        this.time = time;
        this.title = title;
        this.type = type;
        this.url = url;
    }

    @JsonProperty("by")
    public String getBy() {
        return by;
    }

    @JsonProperty("by")
    public void setBy(String by) {
        this.by = by;
    }

    @JsonProperty("descendants")
    public Integer getDescendants() {
        return descendants;
    }

    @JsonProperty("descendants")
    public void setDescendants(Integer descendants) {
        this.descendants = descendants;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("kids")
    public List<Integer> getKids() {
        return kids;
    }

    @JsonProperty("kids")
    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    @JsonProperty("score")
    public Integer getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Integer score) {
        this.score = score;
    }

    @JsonProperty("time")
    public Integer getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Integer time) {
        this.time = time;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("by", by).append("descendants", descendants).append("id", id).append("kids", kids).append("score", score).append("time", time).append("title", title).append("type", type).append("url", url).append("additionalProperties", additionalProperties).toString();
    }



}
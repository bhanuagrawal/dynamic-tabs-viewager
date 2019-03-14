package com.ultimatix.sportskeeda.datamodel;

import com.google.gson.annotations.SerializedName;
import com.ultimatix.sportskeeda.data.entities.Item;

import java.io.Serializable;
import java.util.List;

public class Feed implements Serializable {

    @SerializedName("feed")
    private List<Item> feed = null;

    public List<Item> getFeed() {
        return feed;
    }

    public void setFeed(List<Item> feed) {
        this.feed = feed;
    }

    public Feed withFeed(List<Item> feed) {
        this.feed = feed;
        return this;
    }

}

package com.ultimatix.sportskeeda.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.ultimatix.sportskeeda.datamodel.Author;

import java.io.Serializable;
import java.util.List;

@Entity
public class Item implements Serializable {

    @Embedded
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @SerializedName("thumbnail")
    private String thumbnail;


    @SerializedName("word_count")
    private Integer wordCount;

    @SerializedName("name")
    private String name;


    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private Integer id;

    @ColumnInfo(name = "title")
    @NonNull
    @SerializedName("title")
    private String title;

    @SerializedName("modified_date")
    private String modifiedDate;



    @SerializedName("permalink")
    
    private String permalink;
    @SerializedName("published_date")
    
    private String publishedDate;
    @SerializedName("read_count")
    
    private String readCount;
    @SerializedName("comment_count")
    
    private Integer commentCount;
    @SerializedName("live_traffic")
    
    private Integer liveTraffic;
    @SerializedName("rank")
    
    private Double rank;
    @SerializedName("post_tag")
    @Ignore
    private List<Object> postTag = null;

    @SerializedName("index")
    
    private Integer index;
    @SerializedName("type")
    
    private String type;
    @SerializedName("excerpt")
    
    private String excerpt;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Item withThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Item withWordCount(Integer wordCount) {
        this.wordCount = wordCount;
        return this;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Item withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Item withModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Item withPermalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Item withPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
        return this;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public Item withReadCount(String readCount) {
        this.readCount = readCount;
        return this;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Item withCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public Integer getLiveTraffic() {
        return liveTraffic;
    }

    public void setLiveTraffic(Integer liveTraffic) {
        this.liveTraffic = liveTraffic;
    }

    public Item withLiveTraffic(Integer liveTraffic) {
        this.liveTraffic = liveTraffic;
        return this;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Item withRank(Double rank) {
        this.rank = rank;
        return this;
    }

    public List<Object> getPostTag() {
        return postTag;
    }

    public void setPostTag(List<Object> postTag) {
        this.postTag = postTag;
    }

    public Item withPostTag(List<Object> postTag) {
        this.postTag = postTag;
        return this;
    }


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Item withIndex(Integer index) {
        this.index = index;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item withType(String type) {
        this.type = type;
        return this;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public Item withExcerpt(String excerpt) {
        this.excerpt = excerpt;
        return this;
    }

}

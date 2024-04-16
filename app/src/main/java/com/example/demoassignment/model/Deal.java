package com.example.demoassignment.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deal {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("comments_count")
    @Expose
    private Integer commentsCount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("created_at_in_millis")
    @Expose
    private Long createdAtInMillis;
    @SerializedName("image_medium")
    @Expose
    private String imageMedium;
    @SerializedName("store")
    @Expose
    private Object store;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedAtInMillis() {
        return createdAtInMillis;
    }

    public void setCreatedAtInMillis(Long createdAtInMillis) {
        this.createdAtInMillis = createdAtInMillis;
    }

    public String getImageMedium() {
        return imageMedium;
    }

    public void setImageMedium(String imageMedium) {
        this.imageMedium = imageMedium;
    }

    public Object getStore() {
        return store;
    }

    public void setStore(Object store) {
        this.store = store;
    }

}




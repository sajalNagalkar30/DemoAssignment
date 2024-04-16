package com.example.demoassignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    @SerializedName("seo_settings")
    @Expose
    private SeoSettings seoSettings;
    @SerializedName("deals")
    @Expose
    private List<Deal> deals;

    public SeoSettings getSeoSettings() {
        return seoSettings;
    }

    public void setSeoSettings(SeoSettings seoSettings) {
        this.seoSettings = seoSettings;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

}
package com.github.goldfish07.mypizza.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Crusts {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("defaultSize")
    int defaultSize;
    @SerializedName("sizes")
    List<Sizes> sizes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(int defaultSize) {
        this.defaultSize = defaultSize;
    }

    public List<Sizes> getSizes() {
        return sizes;
    }

    public void setSizes(List<Sizes> sizes) {
        this.sizes = sizes;
    }
}
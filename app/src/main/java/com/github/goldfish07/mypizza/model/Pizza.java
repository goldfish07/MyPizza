package com.github.goldfish07.mypizza.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pizza {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("isVeg")
    Boolean isVeg;
    @SerializedName("description")
    String description;
    @SerializedName("defaultCrust")
    int defaultCrust;
    @SerializedName("crusts")
    List<Crusts> crusts;

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

    public Boolean getVeg() {
        return isVeg;
    }

    public void setVeg(Boolean veg) {
        isVeg = veg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDefaultCrust() {
        return defaultCrust;
    }

    public void setDefaultCrust(int defaultCrust) {
        this.defaultCrust = defaultCrust;
    }

    public List<Crusts> getCrusts() {
        return crusts;
    }

    public void setCrusts(List<Crusts> crusts) {
        this.crusts = crusts;
    }
}



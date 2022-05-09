package com.github.goldfish07.mypizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pizza implements Parcelable {

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
    @SerializedName("size")
    List<Sizes> sizes;


    public Pizza(Pizza pizza) {
        id = pizza.getId();
        name = pizza.getName();
        isVeg = pizza.getVeg();
        description = pizza.getDescription();
        defaultCrust = pizza.getDefaultCrust();
        crusts = pizza.getCrusts();

        for (int i = 0; i < pizza.getCrusts().size(); i++) {
           sizes = pizza.getCrusts().get(i).getSizes();
        }
    }

    public List<Sizes> getSizes() {
        return sizes;
    }

    public void setSizes(List<Sizes> sizes) {
        this.sizes = sizes;
    }

    protected Pizza(Parcel in) {
        id = in.readInt();
        name = in.readString();
        byte tmpIsVeg = in.readByte();
        isVeg = tmpIsVeg == 0 ? null : tmpIsVeg == 1;
        description = in.readString();
        defaultCrust = in.readInt();
    }

    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeByte((byte) (isVeg == null ? 0 : isVeg ? 1 : 2));
        parcel.writeString(description);
        parcel.writeInt(defaultCrust);
    }
}



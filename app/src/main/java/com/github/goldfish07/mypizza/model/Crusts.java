package com.github.goldfish07.mypizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Crusts  implements Parcelable {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("defaultSize")
    int defaultSize;
    @SerializedName("sizes")
    List<Sizes> sizes;

    /**
     * Constructs a new {@link Crusts} object.
     *
     * @param crusts is {@link Crusts} object
     * id is crust's id
     * name is crust's name
     * defaultSize is default crust's size
     * sizes is {@link Sizes} object
     */
    public Crusts(Crusts crusts) {
        id = crusts.getId();
        name = crusts.getName();
        defaultSize = crusts.getDefaultSize();
        sizes = crusts.getSizes();
    }

    protected Crusts(Parcel in) {
        id = in.readInt();
        name = in.readString();
        defaultSize = in.readInt();
    }

    public static final Creator<Crusts> CREATOR = new Creator<Crusts>() {
        @Override
        public Crusts createFromParcel(Parcel in) {
            return new Crusts(in);
        }

        @Override
        public Crusts[] newArray(int size) {
            return new Crusts[size];
        }
    };

    /**
     * @return id of the pizza
     */
    public int getId() {
        return id;
    }

    /**
     * @param id set id of the pizza
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return name of the crust
     */
    public String getName() {
        return name;
    }

    /**
     * @param name of the crust
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return default size of the crust
     */
    public int getDefaultSize() {
        return defaultSize;
    }

    /**
     * @param defaultSize size of the crust
     */
    public void setDefaultSize(int defaultSize) {
        this.defaultSize = defaultSize;
    }

    /**
     * @return crust's sizes
     */
    public List<Sizes> getSizes() {
        return sizes;
    }

    /**
     * @param sizes set crust's sizes list
     */
    public void setSizes(List<Sizes> sizes) {
        this.sizes = sizes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(defaultSize);
    }
}
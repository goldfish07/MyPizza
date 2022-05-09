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

    private boolean isSelected;

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
        isSelected = in.readByte() != 0;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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
package com.github.goldfish07.mypizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Sizes implements Parcelable {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    int price;

    protected Sizes(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
    }

    public static final Creator<Sizes> CREATOR = new Creator<Sizes>() {
        @Override
        public Sizes createFromParcel(Parcel in) {
            return new Sizes(in);
        }

        @Override
        public Sizes[] newArray(int size) {
            return new Sizes[size];
        }
    };

    /**
     * @return id of crust's size
     */
    public int getId() {
        return id;
    }

    /**
     * @param id of crust's size
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return name of crust's size
     */
    public String getName() {
        return name;
    }

    /**
     * @param name of crust's size
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return price of crust's size
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price of crust's size
     */
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(price);
    }
}

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


    /**
     * Constructs a new {@link Pizza} object.
     * @param pizza
     * id is the id of the pizza
     * name is pizza's name
     * isVeg is the food type to find is pizza is Veg or Non-Veg
     * description is information related to pizza
     * crusts is {@link Crusts} object
     */
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
     * @return name of the pizza
     */
    public String getName() {
        return name;
    }

    /**
     * @param name set name of the pizza
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return isVeg of the pizza
     */
    public Boolean getVeg() {
        return isVeg;
    }

    /**
     * @param veg set isVeg of the pizza
     */
    public void setVeg(Boolean veg) {
        isVeg = veg;
    }

    /**
     * @return description of the pizza
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description set description of the pizza
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return defaultCrust of the pizza
     */
    public int getDefaultCrust() {
        return defaultCrust;
    }

    /**
     * @param defaultCrust set defaultCrust of the pizza
     */
    public void setDefaultCrust(int defaultCrust) {
        this.defaultCrust = defaultCrust;
    }

    /**
     * @return {@link Crusts} of the pizza
     */
    public List<Crusts> getCrusts() {
        return crusts;
    }

    /**
     * @param crusts set {@link Crusts} of the pizza
     */
    public void setCrusts(List<Crusts> crusts) {
        this.crusts = crusts;
    }

    /**
     * @return {@link Sizes} of the pizza
     */
    public List<Sizes> getSizes() {
        return sizes;
    }

    /**
     * @param sizes set {@link Sizes} of the pizza
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
        parcel.writeByte((byte) (isVeg == null ? 0 : isVeg ? 1 : 2));
        parcel.writeString(description);
        parcel.writeInt(defaultCrust);
    }
}



package com.github.goldfish07.mypizza.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MyPizza implements Parcelable {

    private String name;
    private String crust;
    private String size;
    private boolean isVeg;
    private int price;

    /**
     * Constructs a new {@link MyPizza} object.
     *
     * @param name  is pizza's name
     * @param crust is pizza's crust
     * @param size  is pizza's size
     * @param isVeg is the food type to find is pizza is Veg or Non-Veg
     * @param price is pizza's price
     */
    public MyPizza(String name, String crust, String size, boolean isVeg, int price) {
        this.name = name;
        this.crust = crust;
        this.size = size;
        this.isVeg = isVeg;
        this.price = price;
    }

    /**
     * Constructs a new {@link MyPizza} object.
     *
     * @param pizza  {@link Pizza} is pizza's object
     * @param crusts is {@link Crusts} object
     * @param sizes  is {@link Sizes} object
     */
    public MyPizza(Pizza pizza, Crusts crusts, Sizes sizes) {
        this.name = pizza.getName();
        this.crust = crusts.getName();
        this.size = sizes.getName();
        this.isVeg = pizza.getVeg();
        this.price = sizes.getPrice();
    }

    /**
     * @return isVeg (true) or Non-Veg (false)
     */
    public boolean isVeg() {
        return isVeg;
    }

    /**
     * @param isVeg (true) or Non-Veg (false)
     */
    public void setVeg(boolean isVeg) {
        this.isVeg = isVeg;
    }

    /**
     * @return name of the pizza
     */
    public String getName() {
        return name;
    }

    /**
     * @param name set pizza's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return pizza's crust name
     */
    public String getCrust() {
        return crust;
    }

    /**
     * @param crust of the pizza
     */
    public void setCrust(String crust) {
        this.crust = crust;
    }

    /**
     * @return size of the pizza
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size of the pizza
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return price of the pizza
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price of the pizza
     */
    public void setPrice(int price) {
        this.price = price;
    }

    protected MyPizza(Parcel in) {
        name = in.readString();
        crust = in.readString();
        size = in.readString();
        byte tmpIsVeg = in.readByte();
        isVeg = tmpIsVeg == 0 ? null : tmpIsVeg == 1;
        price = in.readInt();
    }

    public static final Creator<MyPizza> CREATOR = new Creator<MyPizza>() {
        @Override
        public MyPizza createFromParcel(Parcel in) {
            return new MyPizza(in);
        }

        @Override
        public MyPizza[] newArray(int size) {
            return new MyPizza[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(crust);
        parcel.writeString(size);
        parcel.writeByte((byte) (isVeg ? 1 : 2));
        parcel.writeInt(price);
    }
}

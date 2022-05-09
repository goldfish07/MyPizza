package com.github.goldfish07.mypizza.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MyPizza implements Parcelable {

    String name;
    String crust;
    String size;
    boolean isVeg;

    public boolean isVeg() {
        return isVeg;
    }

    public MyPizza(String name, String crust, String size, boolean isVeg, int price) {
        this.name = name;
        this.crust = crust;
        this.size = size;
        this.isVeg = isVeg;
        this.price = price;
    }

    public void setVeg(boolean veg) {
        isVeg = veg;
    }

    int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyPizza(Pizza pizza, Crusts crusts, Sizes sizes) {
        this.name = pizza.getName();
        this.crust = crusts.getName();
        this.size = sizes.getName();
        this.isVeg = pizza.getVeg();
        this.price = sizes.getPrice();
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

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

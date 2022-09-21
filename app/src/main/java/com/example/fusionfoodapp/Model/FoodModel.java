package com.example.fusionfoodapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FoodModel implements Parcelable {
    private List<ItemModel> menus;


    public FoodModel() {
        this.menus = menus;
    }

    public List<ItemModel> getMenus() {
        return menus;
    }

    public void setMenus(List<ItemModel> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "FoodModel{" +
                "menus=" + menus +
                '}';
    }

    protected FoodModel(Parcel in) {
        menus = in.createTypedArrayList(ItemModel.CREATOR);
    }

    public static final Creator<FoodModel> CREATOR = new Creator<FoodModel>() {
        @Override
        public FoodModel createFromParcel(Parcel in) {
            return new FoodModel(in);
        }

        @Override
        public FoodModel[] newArray(int size) {
            return new FoodModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(menus);
    }
}

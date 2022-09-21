package com.example.fusionfoodapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemModel implements Parcelable {
    int img,total;
    float price;
    String name,url,desc;

    public ItemModel(int img, int total, float price, String name, String url, String desc) {
        this.img = img;
        this.total = total;
        this.price = price;
        this.name = name;
        this.url = url;
        this.desc = desc;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Creator<ItemModel> getCREATOR() {
        return CREATOR;
    }

    protected ItemModel(Parcel in) {
        img = in.readInt();
        total = in.readInt();
        price = in.readFloat();
        name = in.readString();
        url = in.readString();
        desc = in.readString();
    }

    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(img);
        parcel.writeInt(total);
        parcel.writeFloat(price);
        parcel.writeString(name);
        parcel.writeString(url);
        parcel.writeString(desc);
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "img=" + img +
                ", total=" + total +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

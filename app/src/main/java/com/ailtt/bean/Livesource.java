package com.ailtt.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/1/23.
 */

public class Livesource implements Parcelable {
    private int Id;
    private int Type;
    private String name;
    private String VideoUrl;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeInt(this.Type);
        dest.writeString(this.name);
        dest.writeString(this.VideoUrl);
    }

    public Livesource() {
    }

    protected Livesource(Parcel in) {
        this.Id = in.readInt();
        this.Type = in.readInt();
        this.name = in.readString();
        this.VideoUrl = in.readString();
    }

    public static final Parcelable.Creator<Livesource> CREATOR = new Parcelable.Creator<Livesource>() {
        @Override
        public Livesource createFromParcel(Parcel source) {
            return new Livesource(source);
        }

        @Override
        public Livesource[] newArray(int size) {
            return new Livesource[size];
        }
    };
}

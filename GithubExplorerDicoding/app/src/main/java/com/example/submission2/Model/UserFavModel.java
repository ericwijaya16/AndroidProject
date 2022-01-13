package com.example.submission2.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserFavModel implements Parcelable {

    private String username;
    private String name;
    private String follower;
    private String following;
    private String image;
    private String location;
    private String avatarUrl;


    protected UserFavModel(Parcel in) {
        username = in.readString();
        name = in.readString();
        follower = in.readString();
        following = in.readString();
        image = in.readString();
        location = in.readString();
        avatarUrl = in.readString();
    }

    public static final Creator<UserFavModel> CREATOR = new Creator<UserFavModel>() {
        @Override
        public UserFavModel createFromParcel(Parcel in) {
            return new UserFavModel(in);
        }

        @Override
        public UserFavModel[] newArray(int size) {
            return new UserFavModel[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public UserFavModel(String username, String name, String image, String location) {
        this.username = username;
        this.name = name;
        this.follower = follower;
        this.following = following;
        this.image = image;
        this.location = location;
        this.avatarUrl = avatarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(follower);
        dest.writeString(following);
        dest.writeString(image);
        dest.writeString(location);
        dest.writeString(avatarUrl);
    }
}

package com.example.consumerapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDetailModel implements Parcelable {
    private String Login;
    private int id;
    private Object email;
    private int followers;
    private String avatarUrl;
    private int following;
    private String name;
    private String location;


    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Login);
        dest.writeInt(id);
        dest.writeInt(followers);
        dest.writeString(avatarUrl);
        dest.writeInt(following);
        dest.writeString(name);
        dest.writeString(location);
    }

    protected UserDetailModel(Parcel in) {
        Login = in.readString();
        id = in.readInt();
        followers = in.readInt();
        avatarUrl = in.readString();
        following = in.readInt();
        name = in.readString();
        location = in.readString();
    }

    public static final Creator<UserDetailModel> CREATOR = new Creator<UserDetailModel>() {
        @Override
        public UserDetailModel createFromParcel(Parcel in) {
            return new UserDetailModel(in);
        }

        @Override
        public UserDetailModel[] newArray(int size) {
            return new UserDetailModel[size];
        }
    };
}


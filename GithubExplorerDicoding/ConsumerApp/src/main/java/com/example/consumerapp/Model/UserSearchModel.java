package com.example.consumerapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserSearchModel implements Parcelable {


    private String login;
    private String avatar_url;
    private int id;

    public static final Creator<UserSearchModel> CREATOR = new Creator<UserSearchModel>() {
        @Override
        public UserSearchModel createFromParcel(Parcel in) {
            return new UserSearchModel(in);
        }

        @Override
        public UserSearchModel[] newArray(int size) {
            return new UserSearchModel[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(avatar_url);
        dest.writeInt(id);

    }
    protected UserSearchModel(Parcel in) {
        login = in.readString();
        avatar_url = in.readString();
        id = in.readInt();
    }
}

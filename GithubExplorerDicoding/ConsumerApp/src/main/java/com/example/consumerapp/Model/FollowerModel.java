package com.example.consumerapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class FollowerModel implements Parcelable {

	private String login;
	private String avatar_url;
	private int id;


	public String getLogin(){
		return login;
	}

	public String getAvatar_url(){
		return avatar_url;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
	protected FollowerModel(Parcel in) {
		login = in.readString();
		avatar_url = in.readString();
		id = in.readInt();
	}

	public static final Creator<FollowerModel> CREATOR = new Creator<FollowerModel>() {
		@Override
		public FollowerModel createFromParcel(Parcel in) {
			return new FollowerModel(in);
		}

		@Override
		public FollowerModel[] newArray(int size) {
			return new FollowerModel[size];
		}
	};
}
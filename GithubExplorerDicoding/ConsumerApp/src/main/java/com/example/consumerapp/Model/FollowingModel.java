package com.example.consumerapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class FollowingModel implements Parcelable {

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
	protected FollowingModel(Parcel in) {
		login = in.readString();
		avatar_url = in.readString();
		id = in.readInt();
	}

	public static final Creator<FollowingModel> CREATOR = new Creator<FollowingModel>() {
		@Override
		public FollowingModel createFromParcel(Parcel in) {
			return new FollowingModel(in);
		}

		@Override
		public FollowingModel[] newArray(int size) {
			return new FollowingModel[size];
		}
	};
}
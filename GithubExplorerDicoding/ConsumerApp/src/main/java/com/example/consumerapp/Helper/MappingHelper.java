package com.example.consumerapp.Helper;

import android.database.Cursor;

import com.example.consumerapp.Model.UserFavModel;
import com.example.consumerapp.db.DatabaseContract;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<UserFavModel> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<UserFavModel> detailFavUser = new ArrayList<>();

        while (notesCursor.moveToNext()) {

            String username = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.UserColumns.USERNAME));
            String name = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.UserColumns.NAME));
            String image = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.UserColumns.IMAGE));
            String location = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.UserColumns.LOCATION));

            detailFavUser.add(new UserFavModel(username,name,image,location));
        }

        return detailFavUser;
    }

}


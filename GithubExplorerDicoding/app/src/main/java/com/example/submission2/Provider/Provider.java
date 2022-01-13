package com.example.submission2.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.submission2.db.UserHelper;

import static com.example.submission2.db.DatabaseContract.AUTHORITY;
import static com.example.submission2.db.DatabaseContract.UserColumns.CONTENT_URI;
import static com.example.submission2.db.DatabaseContract.TABLE_NAME;

public class Provider extends ContentProvider {

    private static final int num = 1;
    private static final int num_id = 2;
    private UserHelper userHelper;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, TABLE_NAME , num);
        uriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", num_id);
    }

    @Override
    public boolean onCreate() {
        userHelper = UserHelper.getInstance(getContext());
        userHelper.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match(uri)){
            case num:
                cursor = userHelper.reqAll();
                break;

            case num_id:
                cursor = userHelper.reqById(uri.getLastPathSegment());
                break;

            default:
                cursor = null;
                break;
        }

        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id;
        if(uriMatcher.match(uri) == num){
            id = userHelper.insertDB(values);
        } else {
            id = 0;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return Uri.parse(CONTENT_URI + "/" + id);
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int idUpdate;
        if(uriMatcher.match(uri) == num_id){
            idUpdate = userHelper.updateDB(uri.getLastPathSegment(),values);
        } else {
            idUpdate = 0;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return idUpdate;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int idDelete;
        if(uriMatcher.match(uri) == num_id){
            idDelete = userHelper.deleteById(uri.getLastPathSegment());
        } else {
            idDelete = 0;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return idDelete;
    }
}

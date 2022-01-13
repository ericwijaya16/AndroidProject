package com.example.submission2.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String AUTHORITY = "com.example.submission2";
    private static final String SCHEME = "content";

    public static String TABLE_NAME = "user";
    public static final class UserColumns implements BaseColumns {
        public static final String USERNAME = "username";
        public static final String NAME = "name";
        public static final String IMAGE = "image";
        public static final String LOCATION = "location";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

}

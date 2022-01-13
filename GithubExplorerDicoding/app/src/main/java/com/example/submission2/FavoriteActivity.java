package com.example.submission2;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission2.Adapter.FavoriteAdapter;
import com.example.submission2.Helper.MappingHelper;
import com.example.submission2.Model.UserFavModel;
import com.example.submission2.db.DatabaseContract;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteActivity extends AppCompatActivity implements LoadCallback {
    private static final String EXTRA_STATE = "EXTRA_STATE";
    private RecyclerView recyclerViewFav;
    private FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getSupportActionBar().setTitle("Favorite");

        recyclerViewFav = findViewById(R.id.recyclerFavorite);
        recyclerViewFav.setLayoutManager(new LinearLayoutManager(this));
        favoriteAdapter = new FavoriteAdapter(this);
        recyclerViewFav.setAdapter(favoriteAdapter);

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        DataObserver dataObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(DatabaseContract.UserColumns.CONTENT_URI, true, dataObserver);

        if(savedInstanceState == null){
            new LoadAsync(this, this).execute();
        } else {
            ArrayList<UserFavModel> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if(list != null){
                favoriteAdapter.setList(list);
            }
        }
    }

    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(ArrayList<UserFavModel> userFavModel) {
        if(userFavModel.size() > 0){
            favoriteAdapter.setList(userFavModel);
        } else {
            favoriteAdapter.setList(new ArrayList<>());
            showMessage();
        }
    }

    private static class LoadAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadCallback> weakCallback;

        private LoadAsync(Context context, LoadCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        void execute(){
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            weakCallback.get().preExecute();
            executorService.execute(()->{
                Context context = weakContext.get();
                Cursor cursor = context.getContentResolver().query(DatabaseContract.UserColumns.CONTENT_URI,null,null,null,null);
                ArrayList<UserFavModel> userFavModels = MappingHelper.mapCursorToArrayList(cursor);

                handler.post(() -> weakCallback.get().postExecute(userFavModels));
            });
        }
    }

    private void showMessage(){
        Snackbar.make(recyclerViewFav,"No Data", Snackbar.LENGTH_SHORT).show();
    }

    public static class DataObserver extends ContentObserver {

        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean change) {
            super.onChange(change);
            new LoadAsync(context, (LoadCallback) context).execute();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.language_setting){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        } else if(item.getItemId() == R.id.favorite){
            Intent intent = new Intent(FavoriteActivity.this, FavoriteActivity.class);
            startActivity(intent);
        } else if(item.getItemId() == R.id.reminder){
            Intent intent = new Intent(FavoriteActivity.this, AlarmActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}

interface LoadCallback {
    void preExecute();
    void postExecute(ArrayList<UserFavModel> userFavModel);
}
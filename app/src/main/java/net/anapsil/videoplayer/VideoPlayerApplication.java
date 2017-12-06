package net.anapsil.videoplayer;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.anapsil.videoplayer.model.Content;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class VideoPlayerApplication extends Application {
    public static final String DOWNLOADED_CONTENT = "downloaded_content";
    static String assetsLocation;
    static String localAssetsLocation;
    static List<Content> downloadedContent;
    private static SharedPreferences preferences;
    private static Gson gson;
    private final String MEDIA_DIR = "/media";
    private final String PREFERENCE_FILE_KEY = "net.anapsil.videoplayer.preferences";

    public static String getAssetsLocation() {
        return assetsLocation;
    }

    public static void setAssetsLocation(String location) {
        assetsLocation = location;
    }

    public static String getLocalAssetsLocation() {
        return localAssetsLocation;
    }

    public static List<Content> getDownloadedContent() {
        return downloadedContent;
    }

    public static void saveDownloadedContent() {
        preferences.edit().putString(DOWNLOADED_CONTENT, gson.toJson(downloadedContent)).apply();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        createLocalAssetsDirectory();
        loadDownloadedContent();
    }

    private void init() {
        Stetho.initializeWithDefaults(this);
        gson = new Gson();
        preferences = getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE);
    }

    private void createLocalAssetsDirectory() {
        File localAssets = new File(ContextCompat.getDataDir(this).getPath() + MEDIA_DIR);
        localAssets.mkdir();
        localAssetsLocation = localAssets.getPath();
    }

    private void loadDownloadedContent() {
        Type type = new TypeToken<List<Content>>() {
        }.getType();
        String contentJson = preferences.getString(DOWNLOADED_CONTENT, null);
        if (contentJson != null) {
            Log.d(getClass().getName(), contentJson);
            downloadedContent = gson.fromJson(contentJson, type);
        } else {
            downloadedContent = new ArrayList<>();
        }
    }
}

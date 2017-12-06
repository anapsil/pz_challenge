package net.anapsil.videoplayer;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import com.facebook.stetho.Stetho;

import java.io.File;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class VideoPlayerApplication extends Application {
    static String assetsLocation;
    static String localAssetsLocation;
    final String MEDIA_DIR = "/media";

    public static String getAssetsLocation() {
        return assetsLocation;
    }

    public static void setAssetsLocation(String location) {
        assetsLocation = location;
    }

    public static String getLocalAssetsLocation() {
        return localAssetsLocation;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        createLocalAssetsDirectory();
    }

    private void createLocalAssetsDirectory() {
        File localAssets = new File(ContextCompat.getDataDir(this).getPath() + MEDIA_DIR);
        localAssets.mkdir();
        localAssetsLocation = localAssets.getPath();
    }
}

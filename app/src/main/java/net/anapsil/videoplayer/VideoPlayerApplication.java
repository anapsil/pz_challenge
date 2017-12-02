package net.anapsil.videoplayer;

import android.app.Application;

/**
 * Created by anapsil on 02/12/17.
 */

public class VideoPlayerApplication extends Application {
    static String assetsLocation;

    public static String getAssetsLocation() {
        return assetsLocation;
    }

    public static void setAssetsLocation(String location) {
        assetsLocation = location;
    }
}

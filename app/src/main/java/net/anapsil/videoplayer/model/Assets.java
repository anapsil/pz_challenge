package net.anapsil.videoplayer.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

/**
 * Created by anapsil on 02/12/17.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Assets {
    private String assetsLocation;
    private List<Content> objects;

    public Assets() {
    }

    @ParcelConstructor
    public Assets(String assetsLocation, List<Content> objects) {
        this.assetsLocation = assetsLocation;
        this.objects = objects;
    }

    public String getAssetsLocation() {
        return assetsLocation;
    }

    public void setAssetsLocation(String assetsLocation) {
        this.assetsLocation = assetsLocation;
    }

    public List<Content> getObjects() {
        return objects;
    }

    public void setObjects(List<Content> objects) {
        this.objects = objects;
    }
}

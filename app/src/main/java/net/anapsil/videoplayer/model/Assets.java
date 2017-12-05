package net.anapsil.videoplayer.model;

import android.os.Parcelable;

import java.util.List;

/**
 * @author anapsil
 * @since 1.0.0
 */
public class Assets implements Parcelable {
    public static final Parcelable.Creator<Assets> CREATOR = new Parcelable.Creator<Assets>() {
        @Override
        public Assets createFromParcel(android.os.Parcel source) {
            return new Assets(source);
        }

        @Override
        public Assets[] newArray(int size) {
            return new Assets[size];
        }
    };
    private String assetsLocation;
    private List<Content> objects;

    public Assets() {
    }

    protected Assets(android.os.Parcel in) {
        this.assetsLocation = in.readString();
        this.objects = in.createTypedArrayList(Content.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.assetsLocation);
        dest.writeTypedList(this.objects);
    }
}

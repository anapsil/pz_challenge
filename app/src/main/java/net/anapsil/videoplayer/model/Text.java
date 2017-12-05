package net.anapsil.videoplayer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author anapsil
 * @since 1.0.0
 */
public class Text implements Parcelable {
    public static final Parcelable.Creator<Text> CREATOR = new Parcelable.Creator<Text>() {
        @Override
        public Text createFromParcel(Parcel source) {
            return new Text(source);
        }

        @Override
        public Text[] newArray(int size) {
            return new Text[size];
        }
    };
    private String txt;
    private float time;

    public Text() {
    }

    protected Text(Parcel in) {
        this.txt = in.readString();
        this.time = in.readFloat();
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.txt);
        dest.writeFloat(this.time);
    }
}

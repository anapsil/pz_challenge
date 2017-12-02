package net.anapsil.videoplayer.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by anapsil on 02/12/17.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Text {
    private String txt;
    private float time;

    public Text() {
    }

    @ParcelConstructor
    public Text(String txt, float time) {
        this.txt = txt;
        this.time = time;
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
}

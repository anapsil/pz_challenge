package net.anapsil.videoplayer.model;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class Content implements Parcelable {
    public static final Parcelable.Creator<Content> CREATOR = new Parcelable.Creator<Content>() {
        @Override
        public Content createFromParcel(android.os.Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
    private String name;
    private String bg;
    private String im;
    private String sg;
    private List<Text> txts;

    public Content() {
    }

    protected Content(android.os.Parcel in) {
        this.name = in.readString();
        this.bg = in.readString();
        this.im = in.readString();
        this.sg = in.readString();
        this.txts = new ArrayList<Text>();
        in.readList(this.txts, Text.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getSg() {
        return sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    public List<Text> getTxts() {
        return txts;
    }

    public void setTxts(List<Text> txts) {
        this.txts = txts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.bg);
        dest.writeString(this.im);
        dest.writeString(this.sg);
        dest.writeList(this.txts);
    }
}
